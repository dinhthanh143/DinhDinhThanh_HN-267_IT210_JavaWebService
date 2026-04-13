# Hệ thống Quản lý Món Ăn - Bài 3

## Mô tả
Hệ thống quản lý món ăn cho nhà hàng với khả năng tải lên hình ảnh và quản lý dữ liệu tạm trong bộ nhớ.

## Cấu trúc thư mục
```
bai3/
├── config/
│   ├── AppConfig.java          # Cấu hình Spring MVC và multipart resolver
│   └── AppInitializer.java     # Cấu hình upload file và MultipartConfigElement
├── controller/
│   └── FoodController.java    # Controller xử lý yêu cầu thêm và hiển thị món ăn
├── model/
│   └── Food.java              # POJO class cho món ăn
└── README.md                   # Tài liệu hướng dẫn
```

## Các tính năng

### 1. Thêm món ăn mới
- **URL**: `/bai3/food-form`
- **Phương thức**: GET (hiển thị form), POST (xử lý thêm món ăn)
- **Validation**:
  - Kiểm tra file ảnh không được trống
  - Chỉ chấp nhận định dạng: .jpg, .jpeg, .png
  - Giá tiền phải >= 0
  - Tối đa 5MB cho mỗi file

### 2. Xem danh sách món ăn
- **URL**: `/bai3/food-list`
- **Phương thức**: GET
- Hiển thị tất cả món ăn đã thêm với hình ảnh

### 3. Upload file
- **Thư mục lưu trữ**: `C:/RikkeiFood_Temp/`
- **Cấu hình**:
  - Max file size: 5MB
  - Max request size: 10MB
  - File size threshold: 1MB

## Cấu hình Spring MVC

### AppConfig.java
```java
@Bean
public StandardServletMultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
}
```

### AppInitializer.java
```java
MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
    "C:/RikkeiFood_Temp/",  // Location
    5242880,               // Max file size: 5MB
    10485760,              // Max request size: 10MB
    1048576                // File size threshold: 1MB
);
```

## Các danh mục món ăn
- Khai vị
- Món chính
- Món phụ
- Tráng miệng
- Đồ uống
- Khác

## Sử dụng

1. Truy cập form thêm món ăn: `http://localhost:8080/bai3/food-form`
2. Điền thông tin món ăn:
   - Tên món ăn
   - Danh mục
   - Giá tiền
   - Mô tả (tùy chọn)
   - Hình ảnh (bắt buộc)
3. Nhấn "Thêm món ăn"
4. Xem kết quả và danh sách món ăn: `http://localhost:8080/bai3/food-list`

## Lưu ý
- Hệ thống quản lý dữ liệu trong bộ nhớ (static List<Food>)
- Khi restart server, dữ liệu sẽ bị mất
- Thư mục `C:/RikkeiFood_Temp/` sẽ được tự động tạo nếu chưa tồn tại
- Console sẽ in thông tin món vừa thêm và tổng số món ăn sau mỗi lần thêm thành công
