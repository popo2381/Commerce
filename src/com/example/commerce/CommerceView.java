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
        System.out.println("6. 관리자 모드");
    }

    public void menuCategory(Category category) {
        System.out.println("[ " + category.getName() + " 카테고리 ]");
        int i = 1;
        for (Product p : category.getProducts()) {
            System.out.printf(
                    "%d. %-15s | %,9d원 | %-15s | 재고: %d개%n",
                    i++, p.getName(), p.getPrice(), p.getDescription(), p.getstock()
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
            System.out.printf(
                    "%-15s | %,9d원 | %s | 수량: %d개\n%n",
                    p.getName(), p.getPrice(), p.getDescription(), item.getQuantity()
            );
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.printf("%,d원\n%n", cart.getItemsPrice());
    }
    public void adminMenu() {
        System.out.println();
        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }
    public void addProductMenu(List<Category> categories) {
        System.out.println("어느 카테고리의 상품을 추가하시겠습니까?");
        int i = 1;
        for (Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 뒤로가기");
    }
    public void addProductCategory(Category selected) {
        System.out.println("[ " + selected.getName() + " 카테고리에 상품 추가 ]");
    }
    public void addProductResult(String name, int price, String desc, int stock) {
        System.out.printf(
                "%-15s | %,9d원 | %s | 재고: %d개\n%n",
                name, price, desc, stock
        );
        System.out.println("위 정보로  상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
    }
    public void updateProductMenu(List<Category> categories) {
        System.out.println("어느 카테고리의 상품을 수정하시겠습니까?");
        int i = 1;
        for (Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 뒤로가기");
    }
    public void ProductInfo(Category selected) {
        int i = 1;
        for (Product p : selected.getProducts()) {
            System.out.printf(
                    "%d. %-15s | %,9d원 | %-15s | 재고: %d개%n",
                    i++, p.getName(), p.getPrice(), p.getDescription(), p.getstock()
            );
        }
    }
    public void updateProductResult(Product product, String name, int price, String desc, int stock) {
        System.out.printf(
                "%-15s | %,9d원 | %s | 재고: %d개로 새롭게 수정되었습니다.\n%n",
                name, price, desc, stock
        );
    }
    public void deleteProductMenu(List<Category> categories) {
        System.out.println("어느 카테고리의 상품을 삭제하시겠습니까?");
        int i = 1;
        for (Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 뒤로가기");
    }
    public void allProduct(List<Category> categories) {
        System.out.println("==========================");
        System.out.println("[ 전체 상품 현황 ]");
        System.out.println();
        int i = 1;
        for (Category c : categories) {
            System.out.println("* " + c.getName() + " 카테고리 *");
            for (Product p : c.getProducts()) {
                System.out.printf(
                        "%d. %-15s | %,9d원 | %-15s | 재고: %d개%n",
                        i++, p.getName(), p.getPrice(), p.getDescription(), p.getstock()
                );
            }
            System.out.println("==========================");
        }
    }
}
