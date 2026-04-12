package com.example.restaurant.bai5.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    
    private final Map<Long, String> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public OrderRepository() {
        orders.put(1L, "Don hang Com Tam - 45000 VND");
        orders.put(2L, "Don hang Pho Bo - 55000 VND");
        orders.put(3L, "Don hang Banh Mi - 25000 VND");
    }
    
    public String findById(Long id) {
        return orders.get(id);
    }
    
    public Long createOrder(String orderDescription) {
        Long newId = idGenerator.getAndIncrement();
        orders.put(newId, orderDescription);
        return newId;
    }
    
    public boolean deleteOrder(Long id) {
        return orders.remove(id) != null;
    }
    
    public boolean exists(Long id) {
        return orders.containsKey(id);
    }
}
