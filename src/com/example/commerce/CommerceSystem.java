package com.example.commerce;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Customer customer;
    private Scanner scanner = new Scanner(System.in);
    private Cart cart;

    public CommerceSystem(List<Category> categories, Customer customer) {
        this.categories = categories;
        this.customer = customer;
        cart = new Cart(this.customer);
    }
    // 커머스 플랫폼 시스템 실행
    public void start() {
        while(true) {
            platformMain(); // 플랫폼 메인 메뉴
            int input = inputValue();
            if(input > 0 && input < categories.size()) {
                Category selected = categories.get(input-1);
                menuCategory(selected);
            }
            else if(input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            else if(input == 4 && !cart.isEmpty()) {
                System.out.println("아래와 같이 주문하시겠습니까?\n");
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
                int amount = cart.getItemsPrice();
                System.out.println(String.format("%,d원\n", amount));
                System.out.println("1. 주문 확정    2. 메인으로 돌아가기");
                input = inputValue();
                if(input == 1) {
                    System.out.println(String.format("주문이 완료되었습니다!\n총 금액: %,d원", amount));
                    for (CartItem item : cart.getItems()) {
                        Product p = item.getProduct();
                        int temp = p.getstock();
                        p.decraseStock(item.getQuantity());
                        System.out.println(p.getName() + "의 재고가 " + temp + "개 -> " + p.getstock() + "개로 업데이트 되었습니다.\n");
                    }
                    cart.getItems().clear();
                } else if(input == 2) {
                    platformMain();
                }
                else {
                    System.out.println("존재하지 않는 메뉴 번호입니다.");
                }
            }else if(input == 5 && !cart.isEmpty()) {
                cart.getItems().clear();
                System.out.println("진행중인 주문이 취소되었습니다.\n");
            }
            else {
                System.out.println("존재하지 않는 메뉴 번호입니다.\n");
            }
        }
    }
    // 플랫폼 메인 메뉴
    private void platformMain() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        int i= 1;
        for(Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 종료   | 프로그램 종료");
        // 주문 메뉴
        if(!cart.isEmpty()) {
            System.out.println("\n[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인      | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소        | 진행중인 주문을 취소합니다.");
        }
    }
    // 카테고리 메뉴
    private void menuCategory(Category category) {
        while(true) {
            System.out.println("[ " + category.getName() + " 카테고리 ]");
            int i = 1;
            for(Product p : category.getProducts()) {
                System.out.println(
                        String.format(
                                "%d. %-15s | %,9d원 | %s | 재고: %d개",
                                i++, p.getName(), p.getPrice(), p.getDescription(), p.getstock()
                        )
                );
            }
            System.out.println("0. 뒤로가기");
            int input = inputValue();
            int size = category.getProducts().size();
            if(input > 0 && input < size) {
                Product selected = category.getProducts().get(input-1);
                if(selected.getstock() < 1) {
                    System.out.println(selected.getName() + "의 재고가 부족합니다.\n");
                    continue;
                }
                System.out.println(
                        "선택한 상품: " + selected.getName()
                                + " | " + String.format("%,d원", selected.getPrice())
                                + " | " + selected.getDescription()
                                + " | 재고: " + selected.getstock() + "개\n");
                // 장바구니에 추가
                System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인       2. 취소");
                input = inputValue();
                if(input == 1) {
                    boolean value = cart.addItems(selected, 1);
                    if(value)
                        System.out.println(selected.getName() + "(이)가 장바구니에 추가되었습니다.\n");
                    else
                        System.out.println("재고가 부족합니다.\n");
//                    break;
                }
                else if(input == 2) {
                    System.out.println("취소되었습니다.\n");
                }
                else
                    System.out.println("존재하지 않는 메뉴 번호입니다.\n");
            }
            else if(input == 0)
                break;
            else if (input < 0 || input > 4)
                System.out.println("유효하지 않은 상품 번호입니다.\n");
        }
    }
    public int inputValue() {
        while(true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
}
