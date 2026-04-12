package com.example.restaurant.bai5.model;

public class OrderStatistics {
    private long totalOrders;
    private long pendingOrders;
    private long completedOrders;
    private double totalRevenue;
    
    public OrderStatistics() {}
    
    public OrderStatistics(long totalOrders, long pendingOrders, long completedOrders, double totalRevenue) {
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.completedOrders = completedOrders;
        this.totalRevenue = totalRevenue;
    }
    
    public long getTotalOrders() {
        return totalOrders;
    }
    
    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }
    
    public long getPendingOrders() {
        return pendingOrders;
    }
    
    public void setPendingOrders(long pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
    
    public long getCompletedOrders() {
        return completedOrders;
    }
    
    public void setCompletedOrders(long completedOrders) {
        this.completedOrders = completedOrders;
    }
    
    public double getTotalRevenue() {
        return totalRevenue;
    }
    
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
    @Override
    public String toString() {
        return "OrderStatistics{" +
                "totalOrders=" + totalOrders +
                ", pendingOrders=" + pendingOrders +
                ", completedOrders=" + completedOrders +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
