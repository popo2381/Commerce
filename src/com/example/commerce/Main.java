package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 고객 추가
        Customer customer = new Customer("janet", "janet@address.com", "bronze");
        // 카테고리 생성
        List<Category> categories = new ArrayList<>();
        // 전자제품 카테고리 생성
        Category electronics = new Category("전자제품");
        // 카테고리에 전자제품 추가
        categories.add(electronics);
        // 전자제품 카테고리에 상품 추가
        electronics.addProduct(new Product("Galaxy S25", "최신 안드로이드 스마트폰", 1200000, 25));
        electronics.addProduct(new Product("iPhone 16", "Apple의 최신 스마트폰", 1350000, 30));
        electronics.addProduct(new Product("MacBook Pro", "M3 칩셋이 탑재된 노트북", 2400000, 1));
        electronics.addProduct(new Product("AirPods Pro", "노이즈 캔슬링 무선 이어폰", 350000, 50));

        Category clothing = new Category("의류");
        categories.add(clothing);

        Category food = new Category("식품");
        categories.add(food);
        // 커머스 플랫폼 생성
        CommerceSystem system = new CommerceSystem(categories, customer, scanner);
        // 플랫폼 실행
        system.start();
    }
}
