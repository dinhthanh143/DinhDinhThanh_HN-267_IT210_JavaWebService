# PHẦN 1: PHÂN TÍCH LOGIC

| # | Vị trí (Dòng/Thành phần) | Loại vấn đề | Mô tả chi tiết hậu quả |
|---|---|---|---|
| 1 | `<%! private int requestCounter = 0; %>` | Thread-Safety / Race Condition | Biến được khai báo trong thẻ Declaration (`<%! %>`) trở thành biến instance của Servlet. Khi có nhiều người dùng truy cập đồng thời, việc tăng giá trị biến không được đồng bộ hóa, dẫn đến số liệu thống kê sai lệch hoặc xung đột tài nguyên. |
| 2 | `<!-- -->` | HTML Comment vs JSP Comment | Sử dụng chú thích HTML (`<!-- -->`) khiến nội dung chú thích vẫn được gửi về trình duyệt của khách hàng. Điều này làm tăng dung lượng file truyền tải và có thể lộ thông tin logic mã nguồn (Security Leak), thay vì dùng chú thích JSP (`<%-- --%>`). |
| 3 | `<%= sv.getScore() %>;` | Sử dụng sai cú pháp JSP Expression | Thẻ Expression (`<%= %>`) bản chất được chuyển đổi thành lệnh `out.print()`. Việc dư dấu chấm phẩy `;` bên trong thẻ này sẽ khiến giá trị hiển thị kèm theo ký tự `;` thừa, gây mất thẩm mỹ và sai lệch dữ liệu số. |
| 4 | Toàn bộ khối logic if-else xếp loại | Vi phạm nguyên tắc Separation of Concerns | Việc thực hiện logic nghiệp vụ (Business Logic) trực tiếp trong View khiến mã nguồn JSP rối rắm, khó bảo trì và khó viết Unit Test. Theo mô hình MVC, logic này nên được xử lý ở layer Service hoặc Model. |
| 5 | `<%= sv.getFullName() %>` | Lỗ hổng bảo mật XSS (Cross-Site Scripting) | Dữ liệu được hiển thị trực tiếp bằng Expression mà không qua bộ lọc (như JSTL `<c:out>`). Nếu `fullName` chứa mã JavaScript độc hại, trình duyệt có thể thực thi mã đó, dẫn đến nguy cơ mất session hoặc chèn liên kết độc hại. |
