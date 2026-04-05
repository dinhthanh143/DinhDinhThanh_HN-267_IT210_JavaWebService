# Báo cáo thiết kế hệ thống cảnh báo tài khoản

## Nghiệp vụ
Cảnh báo người chơi khi số dư tài khoản sắp hết (< 5.000 VNĐ).

**Input:** username (String), balance (double)  
**Output:** Kích hoạt phương thức cảnh báo tương ứng (Âm thanh hoặc Popup) hoặc thông báo lỗi nếu dữ liệu sai.

## Kỹ thuật DI sử dụng
**Constructor Injection**

### Lý do chọn Constructor Injection:
- **Tính bất biến:** Có thể sử dụng từ khóa `final` để đảm bảo dependency không bị thay đổi suốt vòng đời
- **Dễ dàng Unit Test:** Có thể tự tay truyền mock object vào qua constructor mà không cần Spring Context
- **An toàn:** Spring phát hiện lỗi thiếu phụ thuộc ngay khi khởi động ứng dụng
- **Tính đóng gói:** Đảm bảo encapsulation, không vi phạm quyền truy cập private

## Giải pháp Loose Coupling
Sử dụng **Interface NotificationService** để PlaySessionService không bị phụ thuộc cứng vào một kiểu cảnh báo cụ thể.

### Kiến trúc:
```
PlaySessionService
    ↓ (dependency injection)
NotificationService (Interface)
    ↓                ↓
SoundNotifier    PopupNotifier
```

### Lợi ích:
- **Linh hoạt:** Dễ dàng thay đổi loại cảnh báo mà không sửa code PlaySessionService
- **Mở rộng:** Dễ thêm mới các loại cảnh báo khác (Email, SMS, v.v.)
- **Testable:** Dễ giả lập (mock) NotificationService để test logic nghiệp vụ
- **Bảo trì:** Giảm sự phụ thuộc giữa các module, dễ bảo trì và nâng cấp