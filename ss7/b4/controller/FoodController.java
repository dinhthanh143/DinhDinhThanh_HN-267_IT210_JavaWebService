package org.example.ss07.bai4.controller;

import org.example.ss07.bai4.model.Food;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    private static final List<Food> foodList = new ArrayList<>();
    private static int nextId = 1;

    @GetMapping("/bai4/food-form")
    public String showFoodForm(Model model) {
        model.addAttribute("categories", getCategories());
        return "food-form";
    }

    @PostMapping("/bai4/add-food")
    public String addFood(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Ảnh trống");
                return "redirect:/bai4/food-form";
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !isValidImageFile(originalFilename)) {
                redirectAttributes.addFlashAttribute("error", "SAi định dạng file");
                return "redirect:/bai4/food-form";
            }

            if (price < 0) {
                redirectAttributes.addFlashAttribute("error", "Giá tiền không được âm");
                return "redirect:/bai4/food-form";
            }

            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            long timestamp = System.currentTimeMillis();
            String uniqueFilename = timestamp + "_" + originalFilename;
            
            String uploadPath = "C:/RikkeiFood_Temp/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + uniqueFilename;
            String physicalPath = filePath;
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            Food food = new Food();
            food.setId(nextId++);
            food.setName(name);
            food.setCategory(category);
            food.setPrice(price);
            food.setDescription(description);
            food.setImagePath("/uploads/" + uniqueFilename);
            food.setPhysicalPath(physicalPath);

            foodList.add(food);

            System.out.println("=== Món ăn vừa thêm ===");
            System.out.println(food.toString());
            System.out.println("Tổng số món ăn: " + foodList.size());
            System.out.println("========================");

            redirectAttributes.addFlashAttribute("success", "Thêm món ăn thành công");
            redirectAttributes.addAttribute("id", food.getId());

            return "redirect:/bai4/food-detail/{id}";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi tải lên file: " + e.getMessage());
            return "redirect:/bai4/food-form";
        }
    }

    @GetMapping("/bai4/food-detail/{id}")
    public String showFoodDetail(@PathVariable("id") int id, Model model) {
        Food food = findFoodById(id);
        if (food == null) {
            return "redirect:/bai4/food-list";
        }
        model.addAttribute("food", food);
        return "food-detail";
    }

    @GetMapping("/bai4/food-list")
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

    private Food findFoodById(int id) {
        for (Food food : foodList) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }
}
