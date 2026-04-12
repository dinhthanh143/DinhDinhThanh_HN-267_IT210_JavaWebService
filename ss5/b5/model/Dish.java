package com.example.restaurant.bai5.model;

public class Dish {
    private Long id;
    private String name;
    private Double price;
    private Boolean isAvailable;
    private String description;
    private String category;
    private String imageUrl;
    private Integer preparationTime;
    public Dish() {}
    
    public Dish(Long id, String name, Double price, Boolean isAvailable, String description, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.preparationTime = 15;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Boolean getIsAvailable() {
        return isAvailable;
    }
    
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Integer getPreparationTime() {
        return preparationTime;
    }
    
    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }
    
    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", preparationTime=" + preparationTime +
                '}';
    }
}
