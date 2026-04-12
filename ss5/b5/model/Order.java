package com.example.restaurant.bai5.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String orderNotes;
    private LocalDateTime orderTime;
    private String status; // PENDING, CONFIRMED, DELIVERING, COMPLETED, CANCELLED
    private List<OrderItem> items;
    private String promoCode;
    
    public Order() {}
    
    public Order(Long id, String customerName, String customerPhone, String customerAddress) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.orderTime = LocalDateTime.now();
        this.status = "PENDING";
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getCustomerAddress() {
        return customerAddress;
    }
    
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    public String getOrderNotes() {
        return orderNotes;
    }
    
    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }
    
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<OrderItem> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public String getPromoCode() {
        return promoCode;
    }
    
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", orderTime=" + orderTime +
                ", status='" + status + '\'' +
                ", items=" + items +
                '}';
    }
}
