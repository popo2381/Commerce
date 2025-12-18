package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Customer customer;
    private Scanner scanner = new Scanner(System.in);

    public CommerceSystem(List<Category> categories, Customer customer) {
        this.categories = categories;
        this.customer = customer;
    }
    // 커머스 플랫폼 시스템 실행
    public void start() {
        while(true) {
            printMain();
            int input = scanner.nextInt();

            if(input == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
            Category selected = categories.get(input-1);
            showCategory(selected);
        }
    }
    // 플랫폼 메인 메뉴
    private void printMain() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        int i= 1;
        for(Category c : categories) {
            System.out.println(i++ + ". " + c.getName());
        }
        System.out.println("0. 종료   | 프로그램 종료");
    }
    // 카테고리 메뉴
    private void showCategory(Category category) {
        while(true) {
            System.out.println("[" + category.getName() + " 카테고리 ]");
            int i = 1;
            for(Product p : category.getProducts()) {
                System.out.println(
                        String.format(
                                "%d. %-15s | %,9d원 | %s",
                                i++, p.getName(), p.getPrice(), p.getDescription()
                        )
                );
            }
            System.out.println("0. 뒤로가기");
            int input = scanner.nextInt();
            if(input == 0)
                break;
            Product selected = category.getProducts().get(input-1);
            System.out.println(
                    "선택한 상품: " +  selected.getName()
                     + " | " + String.format("%,d원",selected.getPrice())
                     + " | " + selected.getDescription()
                     + " | 재고: " + selected.getQuantity() + "개\n");
            break;
        }
    }
}
