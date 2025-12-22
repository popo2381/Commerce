package com.example.commerce;

import java.util.List;

public class ProductService {
    public List<Product> getProducts(Category category, int menu) {
        List<Product> products = category.getProducts();
        return switch (menu) {
            case 1 -> products;
            case 2 -> category.getProducts().stream()
                        .filter(p -> p.getPrice() <= 1000000)
                        .toList();
            case 3 -> category.getProducts().stream()
                        .filter(p -> p.getPrice() > 1000000)
                        .toList();
            default -> List.of();
        };
    }
}
