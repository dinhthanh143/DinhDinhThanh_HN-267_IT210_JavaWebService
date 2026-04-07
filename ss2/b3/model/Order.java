package com.example.b3.model;

import java.util.Date;

public class Order {
    private String id;
    private String productName;
    private double amount;
    private Date orderDate;

    public Order(String id, String productName, double amount, Date orderDate) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}