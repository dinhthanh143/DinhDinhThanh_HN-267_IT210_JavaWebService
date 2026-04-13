package org.example.ss07.bai3.controller;

import org.example.ss07.bai3.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FoodController {

    private static final List<Food> foodList = new ArrayList<>();
    private static int nextId = 1;

    @GetMapping("/bai3/food-form")
    public String showFoodForm(Model model) {
        model.addAttribute("categories", getCategories());
        return "food-form";
    }

    @PostMapping("/bai3/add-food")
    public String addFood(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile file,
            Model model) {

        try {
            if (file.isEmpty()) {
                model.addAttribute("error", "Ảnh trống");
                model.addAttribute("categories", getCategories());
                return "food-form";
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !isValidImageFile(originalFilename)) {
                model.addAttribute("error", "SAi định dạng file");
                model.addAttribute("categories", getCategories());
                return "food-form";
            }

            if (price < 0) {
                model.addAttribute("error", "Giá tiền không được âm");
                model.addAttribute("categories", getCategories());
                return "food-form";
            }

            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            
            String uploadPath = "C:/RikkeiFood_Temp/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + uniqueFilename;
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            Food food = new Food();
            food.setId(nextId++);
            food.setName(name);
            food.setCategory(category);
            food.setPrice(price);
            food.setDescription(description);
            food.setImagePath("/uploads/" + uniqueFilename);

            foodList.add(food);

            System.out.println("=== Món ăn vừa thêm ===");
            System.out.println(food.toString());
            System.out.println("Tổng số món ăn: " + foodList.size());
            System.out.println("========================");

            model.addAttribute("success", "Thêm món ăn thành công");
            model.addAttribute("food", food);
            model.addAttribute("totalFoods", foodList.size());

        } catch (IOException e) {
            model.addAttribute("error", "Lỗi khi tải lên file: " + e.getMessage());
            model.addAttribute("categories", getCategories());
        }

        return "food-form";
    }

    @GetMapping("/bai3/food-list")
    public String showFoodList(Model model) {
        model.addAttribute("foods", foodList);
        model.addAttribute("totalFoods", foodList.size());
        return "food-list";
    }

    private List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Khai vị");
        categories.add("Món chính");
        categories.add("Món phụ");
        categories.add("Tráng miệng");
        categories.add("Đồ uống");
        categories.add("Khác");
        return categories;
    }

    private boolean isValidImageFile(String filename) {
        String lowerCaseFilename = filename.toLowerCase();
        return lowerCaseFilename.endsWith(".jpg") || 
               lowerCaseFilename.endsWith(".jpeg") || 
               lowerCaseFilename.endsWith(".png");
    }
}
