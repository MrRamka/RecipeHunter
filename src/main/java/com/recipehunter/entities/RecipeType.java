package com.recipehunter.entities;

public class RecipeType {
    private int id;
    private String name;

    public RecipeType(int id, String name) {
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
