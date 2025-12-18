package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Galaxy S25", "최신 안드로이드 스마트폰", 1200000, 10));
        products.add(new Product("iPhone 16", "Apple의 최신 스마트폰", 1350000, 10));
        products.add(new Product("MacBook Pro", "M3 칩셋이 탑재된 노트북", 2400000, 10));
        products.add(new Product("AirPods Pro", "노이즈 캔슬링 무선 이어폰", 350000, 10));
        commercePlatform(sc, products);
    }
    public static void commercePlatform(Scanner sc, List<Product> products) {
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
        System.out.print("Please enter a number : ");
        int input = sc.nextInt();
        if (input == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
        }
    }
}
