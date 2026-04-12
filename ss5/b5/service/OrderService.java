package com.example.restaurant.bai5.service;

import com.example.restaurant.bai5.model.*;
import com.example.restaurant.bai5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    
    private static final double TAX_RATE = 0.10;
    private static final double DELIVERY_FEE = 15000.0; 
    private static final double MIN_ORDER_FOR_FREE_DELIVERY = 200000.0;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private DishService dishService;

    public OrderSummary calculateOrderTotal(Order order) {
        double subtotal = order.getItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        double tax = subtotal * TAX_RATE;
        double deliveryFee = subtotal >= MIN_ORDER_FOR_FREE_DELIVERY ? 0.0 : DELIVERY_FEE;

        OrderSummary summary = new OrderSummary();
        summary.setSubtotal(subtotal);
        summary.setTax(tax);
        summary.setDeliveryFee(deliveryFee);
        summary.setDiscount(0.0);
        summary.setPromoCode(order.getPromoCode());
        summary.setPromoApplied(false);
        return summary;
    }

    public Order processOrder(Order order, Map<String, String> requestParams) {
        order.setItems(resolveOrderItems(requestParams));
        validateOrder(order);
        order.setStatus("PENDING");
        order.setOrderTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    private List<OrderItem> resolveOrderItems(Map<String, String> requestParams) {
        List<OrderItem> items = new ArrayList<>();
        requestParams.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("quantity-"))
                .forEach(entry -> {
                    Integer quantity = parseQuantity(entry.getValue());
                    if (quantity > 0) {
                        Long dishId = Long.parseLong(entry.getKey().substring("quantity-".length()));
                        Dish dish = dishService.findById(dishId)
                                .orElseThrow(() -> new IllegalArgumentException("Khong tim thay mon an ID: " + dishId));
                        if (!Boolean.TRUE.equals(dish.getIsAvailable())) {
                            throw new IllegalArgumentException("Mon " + dish.getName() + " dang het hang");
                        }
                        items.add(new OrderItem(dish, quantity));
                    }
                });
        items.sort(Comparator.comparing(item -> item.getDish().getId()));
        return items;
    }

    private Integer parseQuantity(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("So luong khong hop le");
        }
    }

    public void validateOrder(Order order) {
        if (order.getCustomerName() == null || order.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui long nhap ten khach hang");
        }

        if (order.getCustomerPhone() == null || !isValidPhoneNumber(order.getCustomerPhone())) {
            throw new IllegalArgumentException("So dien thoai khong hop le");
        }

        if (order.getCustomerAddress() == null || order.getCustomerAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui long nhap dia chi giao hang");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Vui long chon it nhat 1 mon");
        }

        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() <= 0) {
                throw new IllegalArgumentException("So luong mon an phai lon hon 0");
            }

            Optional<Dish> dish = dishService.findById(item.getDish().getId());
            if (dish.isEmpty() || !Boolean.TRUE.equals(dish.get().getIsAvailable())) {
                throw new IllegalArgumentException("Mon an khong con san sang");
            }
        }
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^0\\d{9,10}$");
    }

    public List<Dish> getAvailableDishes() {
        return dishService.getAvailableDishes();
    }
}
