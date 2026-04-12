package com.example.restaurant.bai2.controller;

import com.example.restaurant.bai2.service.DishService;
import com.example.restaurant.bai2.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bai2")
public class DishController {
    
    @Autowired
    private DishService dishService;
    
    @GetMapping("/dish-list")
    public String showDishList(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "dish-list";
    }
    
    @GetMapping("/dish-list-empty")
    public String showEmptyDishList(Model model) {
        List<Dish> dishes = dishService.getEmptyDishList();
        model.addAttribute("dishes", dishes);
        return "dish-list";
    }
    
    @GetMapping("/dish-list-null")
    public String showNullDishList(Model model) {
        List<Dish> dishes = dishService.getNullDishList();
        model.addAttribute("dishes", dishes);
        return "dish-list";
    }
}
