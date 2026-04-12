package com.example.restaurant.controller;

import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller("legacyControllerBai1")
public class LegacyController {

    private final OrderService orderService;

    @Autowired
    public LegacyController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/bai1/orders")
    @ResponseBody
    public String getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/bai1/orders/{id}")
    @ResponseBody
    public String getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/bai2/menu")
    @ResponseBody
    public String getMenu(@RequestParam(value = "category", required = false, defaultValue = "chay") String category) {
        return "Thuc don loai: " + category;
    }

    @GetMapping("/bai3/orders/{id}")
    @ResponseBody
    public String getOrderDetail(@PathVariable Long id) {
        return "Chi tiet don hang so " + id;
    }

    @GetMapping("/bai4/products")
    public String getProducts(
            @RequestParam("category") String category,
            @RequestParam("limit") Integer limit,
            ModelMap modelMap) {
        modelMap.addAttribute("category", category).addAttribute("limit", limit).addAttribute("message", "Tìm kiếm thành công");
        return "productList";
    }
}
