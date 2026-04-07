Báo cáo phân tích 
1. XSS và cơ chế bảo vệ
XSS (Cross-Site Scripting) là lỗ hổng cho phép kẻ tấn công chèn các đoạn mã script (thường là JavaScript) vào trang web mà người dùng khác sẽ xem. Khi trình duyệt thực thi mã này, kẻ tấn công có thể đánh cắp cookie, session hoặc điều hướng người dùng.

Tại sao <c:out> an toàn hơn?:

Sử dụng ${keyword} sẽ render trực tiếp nội dung từ server. Nếu input là <script>alert(1)</script>, trình duyệt sẽ thực thi nó.

Sử dụng <c:out value="${keyword}"/> mặc định có escapeXml="true". Nó sẽ biến đổi các ký tự đặc biệt thành thực thể HTML (HTML Entities).

So sánh HTML Output:

Direct EL: <script>alert(1)</script> (Trình duyệt chạy script).

<c:out>: &lt;script&gt;alert(1)&lt;/script&gt; (Trình duyệt hiển thị dưới dạng văn bản thuần).

2. So sánh <c:if> và <c:choose>
<c:if>: Dùng cho các điều kiện đơn lẻ (đúng hoặc không làm gì cả).

<c:choose>: Tương đương với switch-case hoặc if-else if-else.

Áp dụng: Trong bài này, phần "Giá vé" và "Vé còn lại" nên dùng <c:choose> vì chúng ta có nhiều kịch bản loại trừ lẫn nhau (Ví dụ: Miễn phí OR Có phí; Hết vé OR Sắp hết OR Còn nhiều). Điều này giúp code rõ ràng và tránh kiểm tra nhiều điều kiện thừa.

3. Lợi ích của <c:url>
Vấn đề giải quyết: <c:url> tự động xử lý Context Path.

Deploy thực tế: Nếu ứng dụng deploy tại /ticketing:

Hardcode href="/events/1/book" sẽ trỏ về localhost:8080/events/1/book (Sai - 404).

<c:url> sẽ tự sinh ra /ticketing/events/1/book (Đúng).

Ngoài ra, <c:url> còn hỗ trợ tự động chèn jsessionid vào URL nếu trình duyệt người dùng tắt Cookie, giúp duy trì phiên làm việc (Session Tracking).