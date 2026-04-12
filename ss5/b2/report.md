Phân tích Luồng I/O cho Thymeleaf Dish List
1. Cấu trúc Đối tượng Dish
   Model: Dish
   Java
   public class Dish {
   private Long id;           
   private String name;       
   private Double price;      
   private Boolean isAvailable;

   // Constructors, getters, setters
   }
   Thuộc tính:
   id: Long - Định danh duy nhất cho món ăn.

name: String - Tên món ăn hiển thị cho khách.

price: Double - Giá bán (VND).

isAvailable: Boolean - Trạng thái phục vụ (Còn hàng / Hết hàng).

2. Sơ đồ Luồng I/O
   Controller (DishController)
   |
   | 1. Gọi Service.getDishList()
   | 2. Nhận List<Dish> từ Service/Database
   | 3. Model.addAttribute("dishes", dishList)
   v
   View (dish-list.html)
   |
   | 4. Kiểm tra: dishes != null && !dishes.isEmpty()
   | 5. Lặp: th:each="dish : ${dishes}"
   | 6. Logic:
   |    - th:if="${dish.isAvailable}" -> "Còn hàng"
   |    - th:unless="${dish.isAvailable}" -> "Hết hàng" (màu đỏ)
   v
   Output: Bảng HTML hiển thị danh sách món ăn hoàn chỉnh.
3. Luồng dữ liệu chi tiết
   Input:
   HTTP GET request: /bai2/dish-list

Dữ liệu nguồn: Trả về List<Dish> từ bộ nhớ hoặc Database.

Processing (Xử lý):
Controller: Điều phối yêu cầu qua DishController.showDishList().

Service: Xử lý logic nghiệp vụ tại DishService.getAllDishes().

View Engine: Thymeleaf biên dịch cú pháp th:* thành mã HTML thuần.

Output:
HTML Table với các cột:

Tên món ăn (th:text="${dish.name}").

Giá (th:text="${#numbers.formatDecimal(dish.price, 0, 'COMMA', 0, 'POINT')}" - Gợi ý: nên định dạng tiền tệ).

Trạng thái (Sử dụng CSS Class để phân biệt màu sắc).