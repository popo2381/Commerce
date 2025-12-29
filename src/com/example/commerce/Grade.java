package com.example.commerce;

public enum Grade {
    BRONZE("BRONZE", 0),
    SILVER("SILVER", 5),
    GOLD("GOLD", 10),
    PLATINUM("PLATINUM", 15),
    DIAMOND("DIAMOND", 30);

    private final String label;
    private final int discountRate;

    Grade(String label, int discountRate) {
        this.label = label;
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public String getLabel() {
        return label;
    }

    public int computeDiscount(int price) {
        return price * discountRate / 100;
    }
    public static Grade fromMenu(int menu) {
        return switch(menu) {
            case 1 -> BRONZE;
            case 2 -> SILVER;
            case 3 -> GOLD;
            case 4 -> PLATINUM;
            case 5 -> DIAMOND;
            default -> throw new IllegalArgumentException("고객 등급을 다시 선택하세요.");
        };
    }
}
