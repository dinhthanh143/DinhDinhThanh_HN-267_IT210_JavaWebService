# Schematic Architecture 3-Tier - Bài 5

## Kiến trúc 3 tầng chuẩn Spring MVC

```
Client (Browser/Postman)
       |
       | HTTP Request
       v
+---------------------+
|   Controller Layer  |  <-- OrderController
| - @RestController   |  - Xem: GET /bai5/orders/{id}
| - Request Mapping   |  - Tạo: POST /bai5/orders
| - Exception Handler |  - Hủy: DELETE /bai5/orders/{id}
+---------------------+
       |
       | Business Logic Calls
       v
+---------------------+
|    Service Layer    |  <-- OrderService
| - @Service         |  - Validation logic
| - @Autowired       |  - Business rules
| - Transaction Mgmt |  - Error handling
+---------------------+
       |
       | Data Access Calls
       v
+---------------------+
|  Repository Layer   |  <-- OrderRepository
| - @Repository       |  - Data storage
| - @Autowired       |  - CRUD operations
| - Data Source       |  - Database simulation
+---------------------+
       |
       v
Data Store (Memory/File/DB)
```

## Data Flow Chi Tiết

1. **GET /bai5/orders/{id}**:
   - Client Request (ID: Long)
   - Controller validates ID type
   - Service processes business logic
   - Repository retrieves order data
   - Response: Order details or error message

2. **POST /bai5/orders**:
   - Client Request (Order data)
   - Controller receives order data
   - Service validates and creates order
   - Repository stores new order
   - Response: Success confirmation

3. **DELETE /bai5/orders/{id}**:
   - Client Request (ID: Long)
   - Controller validates ID type
   - Service checks if order exists
   - Repository removes order
   - Response: Delete confirmation

## Exception Handling Flow

```
Type Mismatch Error (ID = "abc")
       |
       v
Controller catches Exception
       |
       v
@ExceptionHandler processes
       |
       v
Return friendly error message
"ID đơn hàng phải là một số"
```
