package org.example.ss07.bai5.controller;

import org.example.ss07.bai5.model.Combo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ComboController {

    private static final List<Combo> comboList = new ArrayList<>();
    private static int nextId = 1;

    @GetMapping("/bai5/combo-form")
    public String showComboForm(Model model) {
        model.addAttribute("availableItems", getAvailableItems());
        return "combo-form";
    }

    @PostMapping("/bai5/add-combo")
    public String addCombo(
            @RequestParam("name") String name,
            @RequestParam("items") List<String> items,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("banner") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng chọn banner cho combo!");
                return "redirect:/bai5/combo-form";
            }

            if (items == null || items.size() < 2) {
                redirectAttributes.addFlashAttribute("error", "Combo phải có ít nhất 2 món ăn!");
                return "redirect:/bai5/combo-form";
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !isValidImageFile(originalFilename)) {
                redirectAttributes.addFlashAttribute("error", "Chỉ chấp nhận file ảnh có định dạng .jpg, .jpeg, .png!");
                return "redirect:/bai5/combo-form";
            }

            if (price < 0) {
                redirectAttributes.addFlashAttribute("error", "Giá tiền không được âm!");
                return "redirect:/bai5/combo-form";
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

            Combo combo = new Combo();
            combo.setId(nextId++);
            combo.setName(name);
            combo.setItemList(items);
            combo.setPrice(price);
            combo.setDescription(description);
            combo.setBannerPath("/uploads/" + uniqueFilename);
            combo.setPhysicalPath(physicalPath);

            comboList.add(combo);

            System.out.println("=== Combo vừa thêm ===");
            System.out.println(combo.toString());
            System.out.println("====================");

            redirectAttributes.addFlashAttribute("success", "Thêm combo thành công!");
            return "redirect:/bai5/combo-list";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi tải lên file: " + e.getMessage());
            return "redirect:/bai5/combo-form";
        }
    }

    @GetMapping("/bai5/combo-list")
    public String showComboList(Model model) {
        model.addAttribute("combos", comboList);
        model.addAttribute("totalCombos", comboList.size());
        return "combo-list";
    }

    private List<String> getAvailableItems() {
        return Arrays.asList(
            "Phở Bò",
            "Bún Chả",
            "Cơm Tấm",
            "Bánh Mì",
            "Gỏi Cuốn",
            "Chả Giò",
            "Nem Rán",
            "Salad",
            "Nước Suối",
            "Nước Ngọt",
            "Trà Sữa",
            "Cà Phê",
            "Sinh Tố",
            "Kem",
            "Chuối Chiên"
        );
    }

    private boolean isValidImageFile(String filename) {
        String lowerCaseFilename = filename.toLowerCase();
        return lowerCaseFilename.endsWith(".jpg") || 
               lowerCaseFilename.endsWith(".jpeg") || 
               lowerCaseFilename.endsWith(".png");
    }
}
