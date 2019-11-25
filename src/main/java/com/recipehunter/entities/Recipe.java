package com.recipehunter.entities;

import java.util.List;

public class Recipe {
    private int id;
    private String title;
    private String categoryId;
    private String time;
    private String steps;
    private int author_id;
    private List<Ingredient> ingredientList;

    public Recipe(int id, String title, String categoryId, String time, String steps, int author_id, List<Ingredient> ingredientList) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.time = time;
        this.steps = steps;
        this.author_id = author_id;
        this.ingredientList = ingredientList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTime() {
        return time;
    }

    public String getSteps() {
        return steps;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", time='" + time + '\'' +
                ", steps='" + steps + '\'' +
                ", author_id=" + author_id +
                ", ingredientList=" + ingredientList.toString() +
                '}';
    }
}
