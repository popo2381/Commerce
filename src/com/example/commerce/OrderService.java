package com.example.commerce;

public class OrderService {
    public void checkout(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            int before = p.getstock();
            p.decraseStock(item.getQuantity());
            System.out.println(p.getName() + "의 재고가 " + before + "개 -> " + p.getstock() + "개로 업데이트 되었습니다.\n");
        }
        cart.getItems().clear();
    }
}