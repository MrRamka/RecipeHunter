package com.recipehunter.entities;

public enum UserRole {
    ADMIN ("Admin"),
    USER ("User");

    UserRole(String title){
        this.title = title;
    }
    private String title;
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "title='" + title + '\'' +
                '}';
    }
}
