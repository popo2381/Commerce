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
    private AdminMode admin = new AdminMode();

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
            if(select >= 1 && select <= categories.size()) {
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
           if (select == 6) {
                runAdminMode();
                continue;
           }
           System.out.println("존재하지 않는 메뉴 번호입니다.\n");
        }
    }
    private void runCategory(Category category) {
        while(true) {
            view.menuCategory(category);

            int size = category.getProducts().size();
            int select = input.readInt();

            if (select == 0)
                return;
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
            int confirm = input.readInRange(1, 2, "존재하지 않는 메뉴 번호입니다.");
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
            System.out.printf("주문이 완료되었습니다!\n총 금액: %,d원%n", amount);
            orderService.checkout(cart);
        }
        if(confirm == 2) {
            System.out.println();
        }
    }
    private void runAdminMode() {
        while(true) {
            String admin = input.readLine("관리자 비밀번호를 입력해주세요: ");
            if (!admin.equals("admin123")) {
                System.out.println("비밀번호가 틀렸습니다.");
                continue;
            }
            while(true) {
                view.adminMenu();
                int select = input.readInt();
                if (select == 0) {
                    return;
                }
                if (select > 0 && select < 5) {
                    manageProduct(select);
                    continue;
                }
                System.out.println("존재하지 않는 메뉴 번호입니다.");
            }
        }
    }

    private void manageProduct(int select) {
        while (true) {
            if (select == 1) {
                while (true) {
                    view.addProductMenu(categories);
                    select = input.readInt();
                    if (select == 0) return;
                    if (select >= 1 && select <= categories.size()) {
                        Category selected = categories.get(select - 1);
                        view.addProductCategory(selected);

                        String name = input.readLine("상품명을 입력해주세요: ");
                        System.out.print("가격을 입력해주세요: ");
                        int price = input.readInt();
                        String desc = input.readLine("상품 설명을 입력해주세요: ");
                        System.out.print("재고 수량을 입력해주세요: ");
                        int stock = input.readInt();

                        view.addProductResult(name, price, desc, stock);

                        int confirm = input.readInt();
                        if (confirm == 1) {
                            admin.addProduct(selected, name, price, desc, stock);
                            System.out.println("상품이 성공적으로 추가되었습니다!\n");
                            continue;
                        }
                        if (confirm == 2) {
                            System.out.println("취소 되었습니다.");
                            return;
                        }
                        continue;
                    }
                    System.out.println("존재하지 않는 메뉴 번호 입니다.\n");
                }
            }
            if (select == 2) {
                while (true) {
                    view.updateProductMenu(categories);
                    select = input.readInt();
                    if (select == 0) return;
                    if (select >= 1 && select <= categories.size()) {
                        Category selected = categories.get(select-1);
                        if(!selected.getProducts().isEmpty()) {
                            view.ProductInfo(selected);
                            System.out.print("수정할 상품번호를 입력해주세요: ");
                            select = (input.readInt());
                            if (select >= 1 && select <= selected.getProducts().size()) {
                                Product product = selected.getProducts().get(select - 1);
                                System.out.println();
                                System.out.println("수정할 항목을 작성해주세요");
                                String name = input.readLine("1. 이름: ");

                                System.out.print("2. 가격: ");
                                int price = input.readInt();

                                String desc = input.readLine("3. 설명: ");

                                System.out.print("4. 재고수량: ");
                                int stock = input.readInt();

                                admin.updateProduct(product, name, price, desc, stock);
                                view.updateProductResult(product, name, price, desc, stock);
                                continue;
                            }
                            System.out.println("유효하지 않은 상품 번호입니다.");
                            continue;
                        }
                        System.out.println("해당 카테고리에 상품이 존재하지 않습니다.");
                        continue;
                    }
                    System.out.println("존재하지 않는 메뉴 번호 입니다.\n");
                }
            }
            if (select == 3) {
                while (true) {
                    view.deleteProductMenu(categories);
                    select = input.readInt();
                    if (select == 0) return;
                    if (select >= 1 && select <= categories.size()) {
                        Category selected = categories.get(select - 1);
                        if(!selected.getProducts().isEmpty()) {
                            view.ProductInfo(selected);
                            System.out.print("삭제할 상품번호를 입력해주세요: ");
                            select = (input.readInt());
                            if (select >= 1 && select <= selected.getProducts().size()) {
                                int product = select - 1;
                                String name = selected.getProducts().get(product).getName();
                                admin.deleteProduct(selected, product);
                                System.out.println(name + " 상품이 삭제되었습니다.\n");
                                continue;
                            }
                            System.out.println("유효하지 않은 상품 번호입니다.\n");
                            continue;
                        }
                        System.out.println("해당 카테고리에 상품이 존재하지 않습니다.\n");
                        continue;
                    }
                    System.out.println("존재하지 않는 메뉴 번호 입니다.\n");
                }
            }
            if (select == 4) {
                view.allProduct(categories);
                return;
            }
        }
    }
}
