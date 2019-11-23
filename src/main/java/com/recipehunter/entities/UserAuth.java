package com.recipehunter.entities;

public class UserAuth {
    private String selector;
    private String validator;
    private int userId;

    public UserAuth(String selector, String validator, int userId) {
        this.selector = selector;
        this.validator = validator;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getSelector() {
        return selector;
    }

    public String getValidator() {
        return validator;
    }
}
