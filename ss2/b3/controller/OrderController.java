package com.example.b3.controller;

import com.example.b3.model.Order;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private ServletContext application;

    @GetMapping("/orders")
    public String listOrders(HttpSession session, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        List<Order> orders = Arrays.asList(
                new Order("ORD001", "Laptop Dell XPS", 25000000, new Date()),
                new Order("ORD002", "iPhone 15 Pro", 30000000, new Date()),
                new Order("ORD003", "Bàn phím cơ", 1500000, new Date()));
        model.addAttribute("orders", orders);

        synchronized (application) {
            Integer count = (Integer) application.getAttribute("totalViewCount");
            if (count == null)
                count = 0;
            application.setAttribute("totalViewCount", count + 1);
        }

        return "orders";
    }
}