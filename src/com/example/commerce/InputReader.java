package com.example.commerce;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }
    public int readInt() {
        while(true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
    public int readInRange(int min, int max, String message) {
        while(true) {
            int input = readInt();
            if (input >= min && input <= max) {
                return input;
            }
            System.out.println(message);
        }
    }
    public String readLine(String message) {
        while(true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("다시 입력해주세요.");
        }
    }
}
