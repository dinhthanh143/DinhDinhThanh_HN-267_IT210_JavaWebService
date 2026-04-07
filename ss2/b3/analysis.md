Báo cáo phân tích 
1. Tại sao thông báo lỗi đăng nhập dùng Request Scope?
Lý do: Thông báo lỗi chỉ có giá trị ngay tại thời điểm đăng nhập sai.

Hậu quả nếu dùng Session: Nếu lưu vào Session, sau khi người dùng đăng nhập sai rồi quay lại trang Login sau đó 1 tiếng (hoặc F5 lại), thông báo lỗi vẫn sẽ "treo" ở đó gây hiểu lầm. Request Scope đảm bảo thông báo biến mất ngay khi hoàn thành chu kỳ Request-Response.

2. Tại sao totalViewCount dùng Application Scope?
Lý do: Đây là dữ liệu dùng chung cho toàn bộ ứng dụng, bất kể là nhân viên nào truy cập.

Hậu quả nếu dùng Session: Nếu dùng Session, mỗi nhân viên sẽ có một bộ đếm riêng bắt đầu từ 1. Khi đó, con số hiển thị không phản ánh đúng tổng lượt xem của cả cửa hàng mà chỉ là số lần "nhân viên đó" tự xem.

3. Race Condition và cách phòng tránh
Race Condition là gì? Là tình huống xảy ra khi nhiều luồng (nhiều nhân viên truy cập cùng lúc) cùng đọc và ghi vào một biến dùng chung (totalViewCount) tại cùng một thời điểm.

Vấn đề trong code cũ:

Luồng A đọc count = 5.

Luồng B đọc count = 5.

Luồng A tăng count lên 6 và lưu lại.

Luồng B cũng tăng giá trị nó vừa đọc (5) lên 6 và lưu lại.
-> Kết quả là 2 lượt xem nhưng bộ đếm chỉ tăng 1.

Cách phòng tránh: Sử dụng khối synchronized(application) để đảm bảo tại một thời điểm chỉ có duy nhất một luồng được phép truy cập và thay đổi giá trị của bộ đếm.