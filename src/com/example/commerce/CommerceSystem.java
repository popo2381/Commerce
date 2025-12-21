package com.example.commerce;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Customer customer;
    private Cart cart;
    private InputReader input;
    private CommerceView view = new CommerceView();
    private OrderService orderService = new OrderService();

    public CommerceSystem(List<Category> categories, Customer customer, Scanner scanner) {
        this.categories = categories;
        this.customer = customer;
        cart = new Cart(this.customer);
        input = new InputReader(scanner);
    }
    // 커머스 플랫폼 시스템 실행
    public void start() {
        while(true) {
            view.platformMain(categories, cart); // 플랫폼 메인 메뉴
            int select = input.readInt();
            if(select == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            if(select > 0 && select < categories.size()) {
                Category selected = categories.get(select-1);
                runCategory(selected);
                continue;
            }
           if(select == 4 && !cart.isEmpty()) {
               runCheckout();
               continue;
            }
           if(select == 5 && !cart.isEmpty()) {
               cart.getItems().clear();
               System.out.println("진행중인 주문이 취소되었습니다.\n");
               continue;
            }
           System.out.println("존재하지 않는 메뉴 번호입니다.\n");
        }
    }
    private void runCategory(Category category) {
        while (true) {
            view.menuCategory(category);

            int size = category.getProducts().size();
            int select = input.readInt();

            if (select == 0)
                break;
            if (select < 0 || select > size) {
                System.out.println("유효하지 않은 상품 번호입니다.\n");
                continue;
            }
            Product selected = category.getProducts().get(select - 1);
            if (selected.getstock() < 1) {
                System.out.println(selected.getName() + "의 재고가 부족합니다.\n");
                continue;
            }
            view.selectedProduct(selected);
            int confirm = input.readInRange(1, 2, "존재하지 않는 메뉴 번호입니다.\n");
            if (confirm == 1) {
                boolean value = cart.addItems(selected, 1);
                if (value) {
                    System.out.println(selected.getName() + "(이)가 장바구니에 추가되었습니다.\n");
                } else {
                    System.out.println("재고가 부족합니다.\n");
                }
            }
            if (confirm == 2) {
                System.out.println("취소되었습니다.\n");
            }
        }
    }
    private void runCheckout() {
        System.out.println("아래와 같이 주문하시겠습니까?\n");
        view.itemCart(cart);
        System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
        int confirm = input.readInRange(1, 2, "존재하지 않는 메뉴 번호입니다.\n");
        if(confirm == 1) {
            int amount = cart.getItemsPrice();
            System.out.println(String.format("주문이 완료되었습니다!\n총 금액: %,d원", amount));
            orderService.checkout(cart);
        }
        if(confirm == 2) {
            System.out.println();
        }
    }
}
