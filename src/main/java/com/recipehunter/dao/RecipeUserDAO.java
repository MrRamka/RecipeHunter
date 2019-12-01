package com.recipehunter.dao;

import com.recipehunter.entities.Recipe;
import com.recipehunter.entities.RecipeUser;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeUserDAO {

    private Connection connection = ConnectionToDatabase.getConnection();

    public int addRecipeUser(int userId, int recipeId) throws SQLException {
        String q = "insert into recipe_user (user_id, recipe_id) values (?, ?)";
        PreparedStatement p = connection.prepareStatement(q);
        p.setInt(1, userId);
        p.setInt(2, recipeId);
        return p.executeUpdate();
    }

    public List<Recipe> getUsersSavedRecipes(int userId, int amount, int start) throws SQLException {
        List<Recipe> recipeUsers = new ArrayList<>();
        String q = "select * from recipe_user where user_id = ? limit ? offset ?";
        PreparedStatement p = connection.prepareStatement(q);
        p.setInt(1, userId);
        p.setInt(2, amount);
        p.setInt(3, start);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()) {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipeUsers.add(recipeDAO.getRecipeById(resultSet.getInt("recipe_id")));
        }
        return recipeUsers;
    }

    public int getAmountSavedRecipes(int id) throws SQLException {
        String q = "select count(user_id) as amount from recipe_user where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("amount");
    }
    public int deleteSavedRecipe(int userId, int recipeId) throws SQLException {
        String q = "delete from recipe_user where user_id = ? and recipe_id = ?";
        PreparedStatement p = connection.prepareStatement(q);
        p.setInt(1, userId);
        p.setInt(2, recipeId);
        return p.executeUpdate();
    }
}
