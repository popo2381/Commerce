package com.example.commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
    // 카테고리 이름 반환
    public String getName() {
        return name;
    }
    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }
    // 카테고리 내 상품 목록 반환
    public List<Product> getProducts() {
        return products;
    }
}
