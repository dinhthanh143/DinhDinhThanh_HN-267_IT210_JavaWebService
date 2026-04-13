BÁO CÁO TỐI ƯU HÓA MÃ NGUỒN DISHCONTROLLER
Phần 1: Phân tích logic (Analysis)
1. Vi phạm nguyên tắc DRY (Don't Repeat Yourself)
   Việc lặp lại đoạn code model.addAttribute("categories", ...) ở cả 3 phương thức vi phạm nghiêm trọng nguyên tắc DRY.

Nguyên tắc này phát biểu rằng: "Mỗi mẩu tri thức (logic) phải có một đại diện duy nhất, không mập mờ, có thẩm quyền trong một hệ thống."

2. Rủi ro bảo trì khi mở rộng hệ thống
   Nếu hệ thống mở rộng ra 20 trang hoặc đơn giản là nhà hàng muốn đổi tên nhóm "Đồ uống" thành "Nước giải khát":

Tốn công sức: Bạn phải đi tìm và sửa thủ công tại 20 địa điểm khác nhau trong code.

Dễ sai sót (Bug): Chỉ cần quên sửa ở 1 trang, dữ liệu trên giao diện sẽ bị bất đồng bộ (trang thì hiển thị tên cũ, trang hiển thị tên mới).

Code "phình to" (Bloated Code): Controller trở nên rác hơn vì chứa quá nhiều dòng code lặp lại, che mất các logic nghiệp vụ quan trọng thực sự của từng hàm.