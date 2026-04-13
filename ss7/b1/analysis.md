BÁO CÁO PHÂN TÍCH VÀ KHẮC PHỤC LỖI DATA BINDING
Phần 1: Phân tích nguyên nhân (Analysis)
1. Tại sao Tên cửa hàng luôn bị null?
   Nguyên nhân: Cơ chế Data Binding của Spring dựa trên việc khớp thuộc tính name của thẻ input với tên trường (field) trong Class POJO.

Lỗi thực tế: Trong file HTML, thẻ input có name="restaurantName", nhưng trong class RestaurantProfile, thuộc tính lại được khai báo là private String name.

Hậu quả: Spring tìm kiếm trường restaurantName trong đối tượng để đổ dữ liệu vào nhưng không thấy, dẫn đến trường name bị bỏ trống (null).

2. Tại sao Checkbox active hoạt động sai logic?
   Vấn đề 1 (Sai giá trị): Thẻ HTML đang để value="MỞ_CỬA". Kiểu dữ liệu trong Java là boolean. Spring không thể tự hiểu chuỗi "MỞ_CỬA" là true hay false.

Vấn đề 2 (Cơ chế Checkbox): Với thẻ HTML thuần, nếu checkbox không được tích, trình duyệt sẽ không gửi bất kỳ dữ liệu nào về Server.

Hậu quả: Spring sẽ gặp lỗi khi cố ép kiểu (Type Mismatch) hoặc nhận dữ liệu sai lệch so với mong muốn của người dùng.