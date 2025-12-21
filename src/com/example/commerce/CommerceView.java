package com.example.commerce;

import java.util.List;

public class CommerceView {
    // 플랫폼 메인 메뉴
    public void platformMain(List<Category> categories, Cart cart) {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        int i = 1;
        for (Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 종료   | 프로그램 종료");
        // 주문 메뉴
        if (!cart.isEmpty()) {
            System.out.println("\n[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인      | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소        | 진행중인 주문을 취소합니다.");
        }
    }

    public void menuCategory(Category category) {
        System.out.println("[ " + category.getName() + " 카테고리 ]");
        int i = 1;
        for (Product p : category.getProducts()) {
            System.out.println(
                    String.format(
                            "%d. %-15s | %,9d원 | %s | 재고: %d개",
                            i++, p.getName(), p.getPrice(), p.getDescription(), p.getstock()
                    )
            );
        }
        System.out.println("0. 뒤로가기");
    }

    public void selectedProduct(Product selected) {
        System.out.println(
                "선택한 상품: " + selected.getName()
                        + " | " + String.format("%,d원", selected.getPrice())
                        + " | " + selected.getDescription()
                        + " | 재고: " + selected.getstock() + "개\n");
        // 장바구니에 추가
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");
    }
    public void itemCart(Cart cart) {
        System.out.println("[ 장바구니 내역 ]");
        for (CartItem item : cart.getItems()) {
            Product p = item.getProduct();
            System.out.println(
                    String.format(
                            "%-15s | %,9d원 | %s | 수량: %d개\n",
                            p.getName(), p.getPrice(), p.getDescription(), item.getQuantity()
                    )
            );
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(String.format("%,d원\n", cart.getItemsPrice()));
    }
}
