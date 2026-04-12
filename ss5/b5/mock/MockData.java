package com.example.restaurant.bai5.mock;

import com.example.restaurant.bai5.model.Dish;
import com.example.restaurant.bai5.model.Order;
import com.example.restaurant.bai5.model.OrderItem;
import com.example.restaurant.bai5.model.OrderSummary;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockData {
    
    public static Dish getDefaultDish() {
        return new Dish(1L, "Pho Bo", 45000.0, true, 
            "Pho bo traditional voi gia vi dam da", "Mon chinh", 
            "/images/pho-bo.jpg");
    }
    
    public static List<Dish> getDefaultDishes() {
        return Arrays.asList(
            new Dish(1L, "Pho Bo", 45000.0, true, 
                "Pho bo traditional voi gia vi dam da", "Mon chinh", 
                "/images/pho-bo.jpg"),
            new Dish(2L, "Bun Cha", 55000.0, true, 
                "Bun cha Ha Noi voi nem chua", "Mon chinh", 
                "/images/bun-cha.jpg"),
            new Dish(3L, "Com Tam", 40000.0, false, 
                "Com tam Suon kho qua trùng", "Mon chinh", 
                "/images/com-tam.jpg")
        );
    }
    
    public static Order getDefaultOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("Nguyen Van A");
        order.setCustomerPhone("0901234567");
        order.setCustomerAddress("123 Nguyen Hue, Q.1, TP.HCM");
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PENDING");
        
        OrderItem item1 = new OrderItem();
        item1.setDish(getDefaultDish());
        item1.setQuantity(2);
        item1.setUnitPrice(45000.0);
        
        order.setItems(Arrays.asList(item1));
        return order;
    }
    
    public static OrderSummary getDefaultOrderSummary() {
        OrderSummary summary = new OrderSummary();
        summary.setSubtotal(90000.0);
        summary.setTax(9000.0);
        summary.setDeliveryFee(15000.0);
        summary.setDiscount(0.0);
        summary.setTotal(114000.0);
        return summary;
    }
    
    public static String getDefaultPromoCode() {
        return "SAVE10";
    }
    
    public static Double getDefaultDiscount() {
        return 10.0;
    }
}
