package com.example.commerce;

import java.util.List;

public class CommerceSystem {
    private List<Product> products;

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    public List<Product> getproducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
}
