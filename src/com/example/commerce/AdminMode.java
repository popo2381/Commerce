package com.example.commerce;

public class AdminMode {

    public void addProduct(Category category, String name, int price, String desc, int stock) {
        category.addProduct(new Product(name, desc, price, stock));
    }
    public void updateProduct(Product product, String name, int price, String desc, int stock) {
        product.setName(name);
        product.setPrice(price);
        product.setDesc(desc);
        product.setStock(stock);
    }
    public void deleteProduct(Category category, int select) {
        category.getProducts().remove(select);
    }
}
