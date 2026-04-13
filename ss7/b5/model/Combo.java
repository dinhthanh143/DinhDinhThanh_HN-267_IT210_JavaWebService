package org.example.ss07.bai5.model;

import java.util.List;

public class Combo {
    private int id;
    private String name;
    private List<String> itemList;
    private double price;
    private String description;
    private String bannerPath;
    private String physicalPath;

    public Combo() {}

    public Combo(int id, String name, List<String> itemList, double price, String description, String bannerPath, String physicalPath) {
        this.id = id;
        this.name = name;
        this.itemList = itemList;
        this.price = price;
        this.description = description;
        this.bannerPath = bannerPath;
        this.physicalPath = physicalPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public String getPhysicalPath() {
        return physicalPath;
    }

    public void setPhysicalPath(String physicalPath) {
        this.physicalPath = physicalPath;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemList=" + itemList +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", bannerPath='" + bannerPath + '\'' +
                ", physicalPath='" + physicalPath + '\'' +
                '}';
    }
}
