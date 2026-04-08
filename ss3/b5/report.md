# Báo cáo Bài 5: Phân tích bẫy dữ liệu và tính idempotent

## 1. Bẫy dữ liệu "Ép kiểu" (Type Mismatch Trap)

### Vấn đề
Nhân viên hay gõ nhầm ID trên URL, ví dụ:
- **Đúng**: `/bai5/orders/5` (ID là số)
- **Sai**: `/bai5/orders/abc` (ID là chữ cái)

### Nguyên nhân
- Spring MVC tự động convert `@PathVariable Long id` từ String sang Long.
- Khi client gõ "abc", không thể convert sang Long.
- Hệ thống ném ra `TypeMismatchException` -> HTTP 400 Bad Request.

### Giải pháp
Sử dụng `@ExceptionHandler` trong Controller:
```java
@ExceptionHandler({NumberFormatException.class, TypeMismatchException.class})
public ResponseEntity<?> handleTypeMismatch(Exception e) {
    return ResponseEntity.badRequest().body("ID đơn hàng phải là một số");
}
```

### Kết quả
- **Trước**: Lỗi 400 hiển thị trang lỗi HTML thô.
- **Sau**: Lỗi 400 hiển thị thông báo thân thiện: "ID đơn hàng phải là một số".

## 2. Phân tích tính Idempotent

### Khái niệm idempotent
Một thao tác là idempotent khi thực hiện nhiều lần cho cùng input vẫn cho cùng kết quả.

### Phân tích các route

#### DELETE /bai5/orders/{id} - **IDEMPOTENT**
**Test an toàn khi bấm 3 lần:**

1. **Lần 1**: DELETE /bai5/orders/5
   - Kiểm tra đơn hàng số 5 tồn tại.
   - Xóa đơn hàng số 5.
   - Kết quả: "Hủy thành công đơn hàng số 5".

2. **Lần 2**: DELETE /bai5/orders/5
   - Kiểm tra đơn hàng số 5 KHÔNG tồn tại.
   - Ném ra exception "Không tìm thấy đơn hàng".
   - Kết quả: 404 Not Found.

3. **Lần 3**: DELETE /bai5/orders/5
   - Giống lần 2.
   - Kết quả: 404 Not Found.

**Kết luận**: DELETE là idempotent vì không tạo ra dữ liệu mới, không gây ra hậu quả phức tạp khi lặp lại.

#### POST /bai5/orders - **KHÔNG IDEMPOTENT**
**Nguy hiểm khi bấm 3 lần:**

1. **Lần 1**: POST /bai5/orders
   - Tạo đơn hàng mới.
   - Kết quả: "Tạo đơn hàng thành công với ID: 4".

2. **Lần 2**: POST /bai5/orders
   - Tạo đơn hàng mới khác.
   - Kết quả: "Tạo đơn hàng thành công với ID: 5".

3. **Lần 3**: POST /bai5/orders
   - Tạo đơn hàng mới khác nữa.
   - Kết quả: "Tạo đơn hàng thành công với ID: 6".

**Hậu quả**: Tạo ra 3 đơn hàng rác trong hệ thống!

### Giải pháp cho POST
Cần có cơ chế phòng chống lặp lại:
- Client disable button sau khi click
- Server kiểm tra duplicate request
- Sử dụng unique transaction ID

### Tổng kết
- **DELETE**: An toàn, idempotent, không lo bấm nhiều lần.
- **POST**: Nguy hiểm, không idempotent, cần cơ chế phòng chống lặp lại.

Đây là lý do quan trọng khi thiết kế API RESTful!
