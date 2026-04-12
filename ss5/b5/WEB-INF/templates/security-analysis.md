# Phân tích Cách Chê ViewResolver Ngän Chän Truy Cáp /WEB-INF/

## 1. Cách hoat dông cua ViewResolver

### 1.1. Request Processing Flow
```
Client Request: http://localhost:8080/WEB-INF/templates/order-quick.html
    |
    v
DispatcherServlet
    |
    v
HandlerMapping (Find Controller)
    |
    v
[NO CONTROLLER FOUND] - Because /WEB-INF/ is not mapped
    |
    v
Return 404 Not Found
```

### 1.2. Normal View Resolution Flow
```
Controller: return "order-quick"
    |
    v
DispatcherServlet
    |
    v
ViewResolver
    |
    v
InternalResourceViewResolver
    |
    v
Resolve: "order-quick" -> "/WEB-INF/templates/order-quick.html"
    |
    v
INTERNAL FORWARD (not external redirect)
    |
    v
Servlet Container processes the resource
    |
    v
HTML Response to Client
```

## 2. Cách chê Báo Mât cua /WEB-INF/

### 2.1. Servlet Container Protection
- **Protected Directory**: `/WEB-INF/` là thu muc dác biêt trong Servlet Specification
- **No Direct Access**: Trình duyêt không thê truy cáp trùc tiép
- **Internal Only**: Chí có thê truy cáp thông qua RequestDispatcher.forward()

### 2.2. Spring Security Layers
```
Layer 1: Servlet Container (Tomcat/Jetty)
  - Blocks direct /WEB-INF/ access
  - Returns 404 for external requests

Layer 2: Spring MVC DispatcherServlet
  - Only processes mapped URLs
  - Ignores unmapped paths

Layer 3: ViewResolver
  - Only resolves view names from controllers
  - Never processes external requests

Layer 4: Application Controllers
  - Explicit mapping to URLs
  - Security checks in controllers
```

## 3. Code ví dô minh ho

### 3.1. Dangerous Attempt (Client Side)
```html
<!-- Client tries to access template directly -->
http://localhost:8080/WEB-INF/templates/order-quick.html
Result: 404 Not Found
```

### 3.2. Safe Access (Server Side)
```java
@Controller
public class OrderController {
    @GetMapping("/order-quick")
    public String showOrderQuick() {
        return "order-quick"; // Safe - internal forward
    }
}
```

### 3.3. Security Handler in Our Code
```java
@GetMapping("/WEB-INF/**")
public String handleWebInfAccess(Model model) {
    throw new SecurityException("Attempted direct access to WEB-INF resources");
}

@ExceptionHandler(SecurityException.class)
public String handleSecurityException(SecurityException ex, Model model) {
    model.addAttribute("error", "Truy cáp không hóp lê!");
    model.addAttribute("securityViolation", true);
    return "error/403";
}
```

## 4. Technical Implementation Details

### 4.1. InternalResourceViewResolver Configuration
```java
@Bean
public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/templates/"); // Protected path
    resolver.setSuffix(".html");
    return resolver;
}
```

### 4.2. Forward vs Redirect
```java
// INTERNAL FORWARD (Safe)
return "order-quick"; // -> /WEB-INF/templates/order-quick.html

// EXTERNAL REDIRECT (Safe but different)
return "redirect:/order-quick"; // -> http://localhost:8080/order-quick
```

### 4.3. RequestDispatcher Forward
```java
// What Spring does internally:
RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/templates/order-quick.html");
rd.forward(request, response); // Internal server-side operation
```

## 5. Attack Scenarios & Prevention

### 5.1. Direct URL Access
```
Attack: http://localhost:8080/WEB-INF/templates/order-quick.html
Prevention: Servlet Container blocks -> 404
```

### 5.2. Path Traversal
```
Attack: http://localhost:8080/../WEB-INF/templates/order-quick.html
Prevention: URL normalization + container security
```

### 5.3. Controller Mapping Bypass
```
Attack: Try to find unmapped paths to templates
Prevention: Explicit controller mappings + security handlers
```

### 5.4. View Parameter Injection
```
Attack: /view?name=../WEB-INF/templates/order-quick
Prevention: Validate view names, whitelist allowed views
```

## 6. Best Practices for Security

### 6.1. Controller Level Security
```java
@PreAuthorize("hasRole('USER')") // Method-level security
@GetMapping("/admin/**")
public String adminOnly() {
    return "admin-page";
}
```

### 6.2. Input Validation
```java
@GetMapping("/view/{viewName}")
public String viewPage(@PathVariable String viewName) {
    // Whitelist approach
    if (!ALLOWED_VIEWS.contains(viewName)) {
        throw new SecurityException("Invalid view name");
    }
    return viewName;
}
```

### 6.3. Error Handling
```java
@ExceptionHandler(SecurityException.class)
public String handleSecurity(SecurityException ex) {
    // Log the attempt
    logger.warn("Security violation: {}", ex.getMessage());
    // Return generic error page
    return "error/403";
}
```

## 7. Monitoring & Logging

### 7.1. Security Event Logging
```java
@EventListener
public void handleSecurityEvent(SecurityEvent event) {
    if (event.getType() == SecurityEventType.WEB_INF_ACCESS) {
        logger.warn("WEB-INF access attempt from IP: {}", event.getIpAddress());
        // Could trigger alerts, block IP, etc.
    }
}
```

### 7.2. Access Logs
```
# Example log entries
2024-04-12 14:30:15 WARN  [Security] WEB-INF access attempt: /WEB-INF/templates/order-quick.html from 192.168.1.100
2024-04-12 14:30:16 INFO  [Security] Blocked suspicious activity from IP: 192.168.1.100
```

## 8. Summary

### 8.1. Why WEB-INF is Secure
1. **Servlet Specification**: Built-in protection by container
2. **No Direct Mapping**: Controllers don't map to /WEB-INF/ paths
3. **Internal Forward Only**: ViewResolver uses internal forwarding
4. **Multiple Security Layers**: Container + Spring + Application

### 8.2. Our Implementation
- **Security Handlers**: Explicit blocking of suspicious paths
- **Exception Handling**: Proper error responses
- **Logging**: Security event tracking
- **Validation**: Input sanitization

### 8.3. Key Takeaways
- `/WEB-INF/` is fundamentally secure by design
- Never expose template paths in URLs
- Always validate user input in view parameters
- Implement comprehensive logging for security events
- Use multiple layers of security (container + framework + application)
