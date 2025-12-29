package com.example.commerce;

import java.util.List;

public class OrderService {
    public void checkout(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int before = p.getStock();
            p.decreaseStock(item.getQuantity());
            System.out.println(p.getName() + "의 재고가 " + before + "개 -> " + p.getStock() + "개로 업데이트 되었습니다.\n");
        }
        cart.getItems().clear();
    }
    public CartItem searchItem(Product product, Cart cart) {
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().equals(product)) {
                    return item;
                }
            }
            return null;
    }
}