package com.example.restaurant.bai5.model;

public class OrderSummary {
    private Double subtotal;
    private Double tax;
    private Double deliveryFee;
    private Double discount;
    private Double total;
    private String promoCode;
    private Boolean promoApplied;
    
    public OrderSummary() {
        this.subtotal = 0.0;
        this.tax = 0.0;
        this.deliveryFee = 0.0;
        this.discount = 0.0;
        this.total = 0.0;
        this.promoApplied = false;
    }
    
    public OrderSummary(Double subtotal, Double tax, Double deliveryFee, Double discount) {
        this.subtotal = subtotal;
        this.tax = tax;
        this.deliveryFee = deliveryFee;
        this.discount = discount;
        this.total = subtotal + tax + deliveryFee - discount;
        this.promoApplied = discount > 0;
    }
    
    // Getters and Setters
    public Double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
        calculateTotal();
    }
    
    public Double getTax() {
        return tax;
    }
    
    public void setTax(Double tax) {
        this.tax = tax;
        calculateTotal();
    }
    
    public Double getDeliveryFee() {
        return deliveryFee;
    }
    
    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
        calculateTotal();
    }
    
    public Double getDiscount() {
        return discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
        this.promoApplied = discount > 0;
        calculateTotal();
    }
    
    public Double getTotal() {
        return total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
    
    public String getPromoCode() {
        return promoCode;
    }
    
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    
    public Boolean getPromoApplied() {
        return promoApplied;
    }
    
    public void setPromoApplied(Boolean promoApplied) {
        this.promoApplied = promoApplied;
    }
    
    private void calculateTotal() {
        if (subtotal != null && tax != null && deliveryFee != null && discount != null) {
            this.total = subtotal + tax + deliveryFee - discount;
        }
    }
    
    @Override
    public String toString() {
        return "OrderSummary{" +
                "subtotal=" + subtotal +
                ", tax=" + tax +
                ", deliveryFee=" + deliveryFee +
                ", discount=" + discount +
                ", total=" + total +
                ", promoCode='" + promoCode + '\'' +
                ", promoApplied=" + promoApplied +
                '}';
    }
}
