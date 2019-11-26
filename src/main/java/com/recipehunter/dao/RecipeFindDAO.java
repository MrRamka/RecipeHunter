package com.recipehunter.dao;

import com.recipehunter.entities.Recipe;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeFindDAO {
    private Connection connection = ConnectionToDatabase.getConnection();
    private RecipeDAO recipeDAO = new RecipeDAO();

    public List<Recipe> getRecipiesByIngredients(Map<String, String> params) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        List<String> activeParams = new ArrayList<>();
        params.forEach((key, value) -> {
            if (value != null) {
                activeParams.add(key);
            }
        });
        StringBuilder queryBuilder = new StringBuilder("select distinct r_id from r_ingredients" +
                " left join r_products on product_id = r_products.id where");
        if (activeParams.size() > 0) {
            for (int i = 0; i < activeParams.size(); i++) {
                 queryBuilder.append(" `name` like \"%" );
                 queryBuilder.append(activeParams.get(i));
                 queryBuilder.append("%\" ");
                 if (i != activeParams.size() -1){
                     queryBuilder.append(" or ");
                 }
            }
            System.out.println(activeParams.toString());
            System.out.println(queryBuilder.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int r_id= resultSet.getInt("r_id");
                Recipe recipe = recipeDAO.getRecipeById(r_id);
                recipes.add(recipe);
            }
        }

        return recipes;
    }
}
