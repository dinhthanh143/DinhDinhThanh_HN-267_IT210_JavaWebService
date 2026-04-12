package com.example.restaurant.bai5.controller;

import com.example.restaurant.bai5.model.Dish;
import com.example.restaurant.bai5.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bai5")
public class DishDetailController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dish/{id}")
    public String showDishDetail(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return dishService.findById(id)
                .map(dish -> {
                    model.addAttribute("dish", dish);
                    return "dish-detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Khong tim thay mon an!");
                    return "redirect:/bai5/order-quick";
                });
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", "Co loi khong mong muon: " + ex.getMessage());
        model.addAttribute("exception", ex);
        return "error";
    }
}
