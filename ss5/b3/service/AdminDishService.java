package com.example.restaurant.bai3.service;

import com.example.restaurant.bai3.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminDishService {
    
    private List<Dish> dishes = new ArrayList<>();
    
    public AdminDishService() {
        dishes.add(new Dish(1L, "Pho Bo", 45000.0, true));
        dishes.add(new Dish(2L, "Bun Cha", 55000.0, true));
        dishes.add(new Dish(3L, "Com Tam", 40000.0, false));
        dishes.add(new Dish(4L, "Banh Mi Thit", 25000.0, true));
        dishes.add(new Dish(5L, "Goi Cuon", 35000.0, false));
        dishes.add(new Dish(6L, "Bun Bo Hue", 50000.0, true));
    }
    
    public List<Dish> getAllDishes() {
        return dishes;
    }
    
    public Optional<Dish> findById(Long id) {
        return dishes.stream()
                .filter(dish -> dish.getId().equals(id))
                .findFirst();
    }
    
    public boolean updateDish(Dish updatedDish) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getId().equals(updatedDish.getId())) {
                dishes.set(i, updatedDish);
                return true;
            }
        }
        return false;
    }
    
    public boolean existsById(Long id) {
        return dishes.stream().anyMatch(dish -> dish.getId().equals(id));
    }
}
