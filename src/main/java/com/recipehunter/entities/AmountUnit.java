package com.recipehunter.entities;

public class AmountUnit {
    private int id;
    private String name;

    public AmountUnit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
