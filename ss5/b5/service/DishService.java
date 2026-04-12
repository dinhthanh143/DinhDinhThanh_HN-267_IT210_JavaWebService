package com.example.restaurant.bai5.service;

import com.example.restaurant.bai5.model.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    
    private List<Dish> dishes = new ArrayList<>();
    
    public DishService() {
        initializeDishes();
    }
    
    private void initializeDishes() {
        dishes.add(createDish(1L, "Pho Bo", 45000.0, true, "Pho bo traditional voi gia vi dam da", "Mon chinh", "/images/pho-bo.jpg", 20));
        dishes.add(createDish(2L, "Bun Cha", 55000.0, true, "Bun cha Ha Noi voi nem chua", "Mon chinh", "/images/bun-cha.jpg", 15));
        dishes.add(createDish(3L, "Com Tam", 40000.0, false, "Com tam suon kho qua trung", "Mon chinh", "/images/com-tam.jpg", 18));
        dishes.add(createDish(4L, "Banh Mi Thit", 25000.0, true, "Banh mi thit nguoi voi rau", "Mon an vat", "/images/banh-mi.jpg", 10));
        dishes.add(createDish(5L, "Goi Cuon", 35000.0, true, "Goi cuon tom thit", "Mon khai vi", "/images/goi-cuon.jpg", 12));
        dishes.add(createDish(6L, "Bun Bo Hue", 50000.0, true, "Bun bo Hue cay ngon", "Mon chinh", "/images/bun-bo-hue.jpg", 22));
        dishes.add(createDish(7L, "Cha Ca La Vong", 65000.0, true, "Cha ca La Vong thom ngon", "Mon chinh", "/images/cha-ca.jpg", 25));
        dishes.add(createDish(8L, "Nem Ran", 28000.0, true, "Nem ran gion tan", "Mon khai vi", "/images/nem-ran.jpg", 15));
    }

    private Dish createDish(Long id, String name, Double price, Boolean available, String description, String category, String imageUrl, Integer preparationTime) {
        Dish dish = new Dish(id, name, price, available, description, category, imageUrl);
        dish.setPreparationTime(preparationTime);
        return dish;
    }
    
    public List<Dish> getAllDishes() {
        return dishes;
    }
    
    public Optional<Dish> findById(Long id) {
        return dishes.stream()
                .filter(dish -> dish.getId().equals(id))
                .findFirst();
    }
    
    public List<Dish> findByCategory(String category) {
        return dishes.stream()
                .filter(dish -> category.equals(dish.getCategory()))
                .toList();
    }
    
    public List<Dish> getAvailableDishes() {
        return dishes.stream()
                .filter(dish -> Boolean.TRUE.equals(dish.getIsAvailable()))
                .toList();
    }
}
