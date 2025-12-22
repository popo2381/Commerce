package com.example.commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public List<CartItem> getItems() {
        return items;
    }
    public int getItemsPrice() {
        int itemsPrice = 0;
        for (CartItem item : items) {
            itemsPrice += item.getItemPrice();
        }
        return itemsPrice;
    }
    public boolean addItems(Product product, int quantity) {
        for (CartItem item : items) {
            if(item.getProduct().equals(product)) {
                if(item.getQuantity() + quantity > product.getStock()) {
                    return false;
                }
                item.increase(quantity);
                return true;
            }
        }
        if(quantity > product.getStock())
            return false;
        items.add(new CartItem(product, quantity));
        return true;
    }
    public boolean compareItem(Product product) {
        for (CartItem item : items) {
            if(item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
    public void removeItem(Product product) {
        items.removeIf(i -> i.getProduct().equals(product));
    }
}
