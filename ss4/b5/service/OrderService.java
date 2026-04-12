package com.example.restaurant.bai5.service;

import com.example.restaurant.bai5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public String getOrderById(Long id) {
        String order = orderRepository.findById(id);
        if (order == null) {
            throw new RuntimeException("Không tìm thây don hàng vôi ID: " + id);
        }
        return order;
    }
    
    public Long createOrder(String orderDescription) {
        if (orderDescription == null || orderDescription.trim().isEmpty()) {
            throw new RuntimeException("Mô tã don hàng không duôc rông");
        }
        return orderRepository.createOrder(orderDescription);
    }
    
    public String deleteOrder(Long id) {
        if (!orderRepository.exists(id)) {
            throw new RuntimeException("Không tìm thây don hàng vôi ID: " + id + " dê hûy");
        }
        boolean deleted = orderRepository.deleteOrder(id);
        if (deleted) {
            return "Hûy thành công don hàng sô " + id;
        }
        throw new RuntimeException("Hûy don hàng thât bai");
    }
}
