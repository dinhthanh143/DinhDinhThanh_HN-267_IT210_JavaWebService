package com.example.restaurant.bai2.service;

import com.example.restaurant.bai2.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    
    public List<Dish> getAllDishes() {
        List<Dish> dishes = new ArrayList<>();
        
        dishes.add(new Dish(1L, "Pho Bo", 45000.0, true));
        dishes.add(new Dish(2L, "Bun Cha", 55000.0, true));
        dishes.add(new Dish(3L, "Com Tam", 40000.0, false));
        dishes.add(new Dish(4L, "Banh Mi Thit", 25000.0, true));
        dishes.add(new Dish(5L, "Goi Cuon", 35000.0, false));
        dishes.add(new Dish(6L, "Bun Bo Hue", 50000.0, true));
        
        return dishes;
    }
    
    public List<Dish> getEmptyDishList() {
        return new ArrayList<>();
    }
    
    public List<Dish> getNullDishList() {
        return null;
    }
}
