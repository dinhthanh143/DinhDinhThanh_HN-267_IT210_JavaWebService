package com.example.restaurant.bai3.controller;

import com.example.restaurant.bai3.model.Dish;
import com.example.restaurant.bai3.service.AdminDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bai3")
public class AdminDishController {
    
    @Autowired
    private AdminDishService adminDishService;
    
    @GetMapping("/dish-list")
    public String showDishList(Model model) {
        List<Dish> dishes = adminDishService.getAllDishes();
        long availableCount = dishes.stream().filter(dish -> Boolean.TRUE.equals(dish.getIsAvailable())).count();

        model.addAttribute("dishes", dishes);
        model.addAttribute("availableCount", availableCount);
        model.addAttribute("unavailableCount", dishes.size() - availableCount);
        return "dish-list";
    }
    
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Dish> dishOptional = adminDishService.findById(id);
        
        if (dishOptional.isPresent()) {
            model.addAttribute("dish", dishOptional.get());
            return "edit-dish";
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy món ăn yêu cầu!");
            return "redirect:/bai3/dish-list";
        }
    }
    
    @PostMapping("/update")
    public String updateDish(@ModelAttribute Dish dish, RedirectAttributes redirectAttributes) {
        if (adminDishService.updateDish(dish)) {
            redirectAttributes.addFlashAttribute("success", "Cập nhật món ăn thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Cập nhật món ăn thất bại!");
        }
        return "redirect:/bai3/dish-list";
    }
}
