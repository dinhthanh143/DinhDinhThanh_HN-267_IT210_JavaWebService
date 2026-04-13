package org.example.ss07.bai2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/merchant/dish")
public class DishController {

    /**
     * PHƯƠNG THỨC DÙNG CHUNG (Duy nhất 1 lần)
     * Spring sẽ tự động add "categories" vào Model cho tất cả các Request trong Controller này.
     */
    @ModelAttribute("categories")
    public List<String> getCategories() {
        System.out.println("==> Đang nạp danh sách nhóm món ăn dùng chung");
        return Arrays.asList("Món chính", "Đồ uống", "Tráng miệng", "Topping");
    }

    // API 1: Mở form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("dish", new Dish());
        return "dish-add";
    }

    // API 2: Mở form chỉnh sửa
    @GetMapping("/edit")
    public String showEditForm(Model model) {
        model.addAttribute("dish", new Dish("Trà sữa", "Đồ uống"));
        return "dish-edit";
    }

    // API 3: Mở trang tìm kiếm
    @GetMapping("/search")
    public String showSearchPage() {
        // Không cần code gì thêm, "categories" đã tự có trong Model nhờ hàm trên
        return "dish-search";
    }
}