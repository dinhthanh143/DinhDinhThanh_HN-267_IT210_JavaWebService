package com.example.restaurant.bai5.model;

public class OrderItem {
    private Long id;
    private Dish dish;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    
    public OrderItem() {}
    
    public OrderItem(Dish dish, Integer quantity) {
        this.dish = dish;
        this.quantity = quantity;
        this.unitPrice = dish.getPrice();
        this.totalPrice = unitPrice * quantity;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Dish getDish() {
        return dish;
    }
    
    public void setDish(Dish dish) {
        this.dish = dish;
        if (dish != null) {
            this.unitPrice = dish.getPrice();
            calculateTotal();
        }
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateTotal();
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        calculateTotal();
    }
    
    public Double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    private void calculateTotal() {
        if (unitPrice != null && quantity != null) {
            this.totalPrice = unitPrice * quantity;
        }
    }
    
    @Override
    public String toString() {
        return "OrderItem{" +
                "dish=" + (dish != null ? dish.getName() : "null") +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
