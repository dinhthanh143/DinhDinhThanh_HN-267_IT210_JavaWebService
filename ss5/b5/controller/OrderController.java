package com.example.restaurant.bai5.controller;

import com.example.restaurant.bai5.model.Order;
import com.example.restaurant.bai5.model.OrderSummary;
import com.example.restaurant.bai5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/bai5")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order-quick")
    public String showOrderQuick(Model model) {
        model.addAttribute("dishes", orderService.getAvailableDishes());
        return "order-quick";
    }

    @PostMapping("/order-submit")
    public String submitOrder(@ModelAttribute Order order,
                              @RequestParam Map<String, String> requestParams,
                              RedirectAttributes redirectAttributes) {
        try {
            Order processedOrder = orderService.processOrder(order, requestParams);
            redirectAttributes.addFlashAttribute("success", "Dat mon thanh cong!");
            return "redirect:/bai5/order-confirmation/" + processedOrder.getId();
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/bai5/order-quick";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Co loi xay ra: " + ex.getMessage());
            return "redirect:/bai5/order-quick";
        }
    }

    @GetMapping("/order-confirmation/{orderId}")
    public String showOrderConfirmation(@PathVariable Long orderId, Model model, RedirectAttributes redirectAttributes) {
        try {
            var orderOptional = orderService.getOrderById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                OrderSummary summary = orderService.calculateOrderTotal(order);
                model.addAttribute("order", order);
                model.addAttribute("orderSummary", summary);
                return "order-confirmation";
            }
            redirectAttributes.addFlashAttribute("error", "Khong tim thay don hang!");
            return "redirect:/bai5/order-quick";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Loi tai thong tin don hang: " + ex.getMessage());
            return "redirect:/bai5/order-quick";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", "Co loi khong mong muon: " + ex.getMessage());
        model.addAttribute("exception", ex);
        return "error";
    }
}
