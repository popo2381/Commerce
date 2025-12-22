package com.example.commerce;

public class Customer {
    private String name;
    private String email;
    private Grade grade;

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
