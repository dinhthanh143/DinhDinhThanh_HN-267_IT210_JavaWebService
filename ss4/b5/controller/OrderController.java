package com.example.restaurant.bai5.controller;

import com.example.restaurant.bai5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.TypeMismatchException;

@RestController
@RequestMapping("/bai5/orders")
public class OrderController {
    
    private final OrderService orderService;
    
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        try {
            String order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody String orderDescription) {
        try {
            Long newOrderId = orderService.createOrder(orderDescription);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Tao don hàng thành công vôi ID: " + newOrderId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            String result = orderService.deleteOrder(id);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @ExceptionHandler({NumberFormatException.class, TypeMismatchException.class})
    public ResponseEntity<?> handleTypeMismatch(Exception e) {
        return ResponseEntity.badRequest().body("ID don hàng phai la mot so");
    }
}
