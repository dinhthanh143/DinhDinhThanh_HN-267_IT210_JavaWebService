# BÁO CÁO PHÂN TÍCH LỖI VÀ HIỆU CHỈNH

## PHẦN 1: PHÂN TÍCH LOGIC

### 1. Phân tích lỗi trong MyWebAppInitializer.java

Việc cấu hình `getServletMappings()` trả về `new String[] { "/api/*" }` xác định phạm vi tiếp nhận yêu cầu của DispatcherServlet.

**Cơ chế hoạt động:** DispatcherServlet chỉ tiếp nhận và xử lý các Request có đường dẫn bắt đầu bằng tiền tố `/api/` (ví dụ: `/api/welcome`, `/api/user`).

**Nguyên nhân lỗi 404:** Khi truy cập đường dẫn `/welcome`, yêu cầu này không khớp với khuôn mẫu (pattern) đã cấu hình. Do đó, yêu cầu không được chuyển đến Spring MVC mà bị chặn lại tại Web Container (Tomcat). Vì không có tài liệu vật lý nào tương ứng với đường dẫn này, hệ thống trả về lỗi 404 Not Found.

### 2. Phân tích lỗi trong WebConfig.java

Cấu hình `@ComponentScan(basePackages = "com.demo.service")` quyết định phạm vi quét các thành phần (Bean) của Spring IoC Container.

**Hậu quả:** Spring chỉ thực hiện quét các lớp có đánh dấu Annotation (@Controller, @Service,...) bên trong package `com.demo.service`.

**Nguyên nhân lỗi:** Lớp WelcomeController được định vị tại package `com.example.mvc3_j11.ss2.b1.controller`, nằm ngoài phạm vi quét đã chỉ định. Do đó, Bean của Controller không được khởi tạo trong ApplicationContext.

**Kết luận:** Ngay cả khi Request đi qua được bộ lọc, Spring cũng không tìm thấy Handler phù hợp để xử lý yêu cầu.

### 3. Tổng hợp đánh giá

Nếu chỉ khắc phục lỗi Servlet Mapping (lỗi 1) mà giữ nguyên lỗi Component Scan (lỗi 2), ứng dụng không thể vận hành.

**Lý do:** Mặc dù DispatcherServlet đã có thể tiếp nhận yêu cầu tại đường dẫn `/`, nhưng hệ thống vẫn thiếu đối tượng Controller để thực thi logic nghiệp vụ. Sự thiếu hụt Bean trong tầng Controller dẫn đến việc Spring không thể ánh xạ (mapping) yêu cầu tới phương thức xử lý tương ứng.
