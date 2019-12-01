package com.recipehunter.dao;

import com.recipehunter.entities.Ingredient;
import com.recipehunter.entities.Recipe;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private Connection connection = ConnectionToDatabase.getConnection();

    public Recipe getRecipeById(int id) throws SQLException {
        String sqlQuery = "select recipes.id, title, `time`, steps, `name`, author from recipes left join r_categories on category_id=r_categories.id where recipes.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        ResultSet recipesResultSet = preparedStatement.executeQuery();

        String sqlIngrQuery = "select DISTINCT r_id, r_products.`name` as pr_name, amount, r_amount_units.`name` from r_ingredients left join r_products on r_products.id=product_id left join r_amount_units on amount_unit_id = r_amount_units.id where r_id = ?";
        PreparedStatement preparedStatementIngr = connection.prepareStatement(sqlIngrQuery);
        preparedStatementIngr.setInt(1, id);
        ResultSet ingredientsResultSet = preparedStatementIngr.executeQuery();

        return getRecipeByResultSet(recipesResultSet, ingredientsResultSet);
    }

    private Recipe getRecipeByResultSet(ResultSet recipesResultSet, ResultSet ingredientsResultSet) throws SQLException {
        while (recipesResultSet.next()) {
            int id = recipesResultSet.getInt("id");
            String title = recipesResultSet.getString("title");
            String categoryId = recipesResultSet.getString("name");
            String time = recipesResultSet.getString("time");
            String steps = recipesResultSet.getString("steps");
            int author_id = recipesResultSet.getInt("author");
            List<Ingredient> ingredients = new ArrayList<>();
            while (ingredientsResultSet.next()) {
                int r_id = ingredientsResultSet.getInt("r_id");
                String name = ingredientsResultSet.getString("pr_name");
                float amount = ingredientsResultSet.getFloat("amount");
                String amount_init = ingredientsResultSet.getString("name");
                Ingredient ingredient = new Ingredient(r_id, name, amount, amount_init);
                ingredients.add(ingredient);
            }
            return new Recipe(id, title, categoryId, time, steps, author_id, ingredients);
        }
        return null;
    }


    public int addRecipe(String title, int category_id, String time, String steps, int author) throws SQLException {
        String q = "insert into recipes (title, category_id, `time`, steps, author) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, category_id);
        preparedStatement.setString(3, time);
        preparedStatement.setString(4, steps);
        preparedStatement.setInt(5, author);
        return preparedStatement.executeUpdate();
    }

    public int getRecipeIdByParams(String name) throws SQLException {
        //, String recipeType, String recipeTime, String recipeDirections
        String q = "select id from recipes where title = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
    public int deleteRecipeIdByParams(String name) throws SQLException {
        String q = "delete from recipes where title = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate();
    }
    public List<Recipe> getRecipesByAuthor(int id, int amount, int start) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String q = "select id from recipes where author = ? limit ? offset ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, amount);
        preparedStatement.setInt(3, start);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            recipes.add(getRecipeById(resultSet.getInt("id")));
        }
        return recipes;


    }
    public int getAmountRecipesByAuthor(int id) throws SQLException {
        String q = "select count(id) as amount from recipes where author = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("amount");
    }


}
