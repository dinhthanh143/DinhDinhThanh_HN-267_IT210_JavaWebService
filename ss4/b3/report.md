Báo cáo bài 3: Phân tích PathVariable vs RequestParam
Phân tích hai cách design URL
Cách A: /bai3/orders/5

=Số 5 là path parameter (tham số trong URI Path)

-Số 5 là thành phần của URL path, không phải query string

-Sử dụng @PathVariable để lấy giá trị

Cách B: /bai3/orders?id=5

-Số 5 là query parameter (tham số trong Query String)

-Số 5 nằm trong phần query string sau dấu ?

-Sử dụng @RequestParam để lấy giá trị

Lựa chọn phù hợp

Em chọn Cách A vì:

-ID là thành phần của tài nguyên, nên là một phần của URL path theo RESTful design.

-URL /orders/5 thể hiện rõ ràng "đơn hàng số 5", dễ hiểu hơn orders?id=5.

-PathVariable phù hợp hơn để lấy ID của đối tượng cụ thể.

-Theo RESTful conventions, GET /resource/{id} là cách tiêu chuẩn để lấy tài nguyên theo ID.

Kết luận:
Cách A phù hợp hơn để lấy thông tin chi tiết của đơn hàng theo ID vì theo RESTful design, ID nên là thành phần của URL path để thể hiện rõ ràng tài nguyên đang truy cập.