package com.example.commerce;

public class Customer {
    private final String name;
    private final String email;
    private final Grade grade;

    public Customer(String name, String email, Grade grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }
    public Grade getGrade(){
        return grade;
    }
}
