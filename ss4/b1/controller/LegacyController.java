package com.example.mvc3_j11.controller;

import com.example.mvc3_j11.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
