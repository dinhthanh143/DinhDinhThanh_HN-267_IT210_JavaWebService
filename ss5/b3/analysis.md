# Phân tích Luồng Data Binding cho Form Chỉnh sửa Món ăn

## 1. Luồng Dữ liệu (Data Flow)

### View A (dish-list.html) → Controller → View B (edit-dish.html)

```
Step 1: User Action
├── User nhấn nút "Chỉnh sửa" trên danh sách món ăn
└── URL: /bai3/edit/{id} (ví dụ: /bai3/edit/3)

Step 2: Controller Processing
├── AdminDishController.editForm(@PathVariable Long id)
├── Gọi AdminDishService.findById(id)
├── Kiểm tra tồn tại của món ăn
│   ├── Nếu tồn tại: Model.addAttribute("dish", dish)
│   └── Nếu không tồn tại: Model.addAttribute("error", "Không tìm thấy món ăn yêu cầu!")
└── Return "edit-dish" hoặc "redirect:/bai3/dish-list"

Step 3: View B Rendering
├── edit-dish.html nhận đối tượng dish qua th:object="${dish}"
├── th:field="*{name}" tự động điền tên món ăn
├── th:field="*{price}" tự động điền giá
├── th:field="*{isAvailable}" tự động chọn trạng thái
└── Form ready for user editing
```

## 2. Các Biểu thức Thymeleaf Sử dụng

### Data Binding Expressions:
- **th:object="${dish}"** - Liên kết form với đối tượng Dish
- **th:field="*{name}"** - Liên kết input với thuộc tính name
- **th:field="*{price}"** - Liên kết input với thuộc tính price
- **th:field="*{isAvailable}"** - Liên kết checkbox với thuộc tính isAvailable

### URL Expressions:
- **@{/bai3/edit/{id}(id=${dish.id})}** - Tạo URL động với ID
- **@{|/bai3/edit/${dish.id}|}** - Literal substitution cho URL
- **@{/bai3/update}** - URL cho form action

### Utility Expressions:
- **${#fields.hasErrors('name')}** - Kiểm tra lỗi validation
- **${#strings.defaultString(dish.name, '')}** - Xử lý null/empty

## 3. Xử lý Kịch bản Đặc biệt

### Case 1: ID không tồn tại
```
Controller Logic:
if (dish == null) {
    model.addAttribute("error", "Không tìm thấy món ăn yêu cầu!");
    return "redirect:/bai3/dish-list";
}
```

### Case 2: Form Submission
```
POST /bai3/update
├── @ModelAttribute Dish dish (tự động bind từ form)
├── Validation logic
├── Service.update(dish)
└── Redirect với success message
```

## 4. Cấu trúc Package bai3

```
bai3/
├── config/
│   ├── AppConfig.java      - Thymeleaf configuration
│   ├── AppInit.java        - Web initializer
│   └── WebConfig.java      - MVC configuration
├── model/
│   └── Dish.java          - Model (reuse hoặc copy từ bai2)
├── service/
│   └── AdminDishService.java - Service với findById, update methods
├── controller/
│   └── AdminDishController.java - Controller với edit/update endpoints
└── templates/
    ├── edit-dish.html      - Form chỉnh sửa
    └── dish-list.html      - Danh sách với nút edit
```

## 5. URL Mapping Design

### GET Endpoints:
- `/bai3/dish-list` - Hiển thị danh sách món ăn
- `/bai3/edit/{id}` - Hiển thị form chỉnh sửa

### POST Endpoints:
- `/bai3/update` - Xử lý cập nhật món ăn

### Error Handling:
- Invalid ID → Redirect với error message
- Validation errors → Hiển thị lại form với errors

## 6. Security Considerations

### ID Validation:
- Kiểm tra ID có tồn tại trong database
- Tránh SQL injection khi tìm kiếm
- Validate ID format (numeric, positive)

### Form Security:
- CSRF protection
- Input validation
- Proper error handling without exposing system details
