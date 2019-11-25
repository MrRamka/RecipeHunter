package com.recipehunter.entities;

public class Ingredient {
    private int id;
    private String name;
    private float amount;
    private String amount_unit;

    public Ingredient(int id, String name, float amount, String amount_unit) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.amount_unit = amount_unit;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getAmount_unit() {
        return amount_unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", amount_unit='" + amount_unit + '\'' +
                '}';
    }
}
