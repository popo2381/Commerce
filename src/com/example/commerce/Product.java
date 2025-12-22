package com.example.commerce;

public class Product {
    private String name;
    private String description;
    private int price;
    private int stock;

    public Product(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int decraseStock(int quantity) {
        stock -= quantity;
        return stock;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDesc(String desc) {
        this.description = desc;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
