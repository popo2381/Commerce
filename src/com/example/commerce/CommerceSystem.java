package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private List<Product> products;
    private Scanner scanner = new Scanner(System.in);

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }
    public void start() {
        printPlatform();
        handleInput();
    }
    private void printPlatform() {
        System.out.println("[ 실시간 커머스 플랫폼   -   전자제품 ]");
        int i = 1;
        for (Product p : products) {
            System.out.println(
                    String.format(
                            "%d. %-15s | %,9d원 | %s",
                            i,
                            p.getName(),
                            p.getPrice(),
                            p.getDescription()
                    )
            );
            i++;
        }
        System.out.printf(
                "%d. %-15s | %s%n",
                0,
                "exit",
                "프로그램 종료"
        );
    }
    private void handleInput() {
        System.out.print("Please enter a number : ");
        int input = scanner.nextInt();
        if (input == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
        }
    }
}
