package com.recipehunter.entities;

public class RecipeUser {
    private int userId;
    private int recipeId;

    public RecipeUser(int userId, int recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
