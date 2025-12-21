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
    public int getstock() {
        return stock;
    }
    public int decraseStock(int quantity) {
        stock -= quantity;
        return stock;
    }
}
