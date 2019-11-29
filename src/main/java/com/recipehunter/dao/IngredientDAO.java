package com.recipehunter.dao;

import com.recipehunter.entities.AmountUnit;
import com.recipehunter.entities.Product;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientDAO {

    private Connection connection = ConnectionToDatabase.getConnection();

    public int addIngredient(int r_id, int product_id, double amount, int amount_unit_id) throws SQLException {
        String q = "insert into r_ingredients (r_id, product_id, amount, amount_unit_id) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setInt(1, r_id);
        preparedStatement.setInt(2, product_id);
        preparedStatement.setDouble(3, amount);
        preparedStatement.setInt(4, amount_unit_id);
        return preparedStatement.executeUpdate();
    }

    public int addIngredient(int r_id, String product_name, double amount, String amount_unit_name) throws SQLException {
       ProductDAO productDAO = new ProductDAO();
        AmountUnitDAO amountUnitDAO = new AmountUnitDAO();
        Product product = productDAO.getProductByName(product_name);
        AmountUnit amountUnit = amountUnitDAO.getAmountUnitByName(amount_unit_name);
        if (product == null) {
            productDAO.addProduct(product_name);
            product = productDAO.getProductByName(product_name);
        }
        if (amountUnit == null) {
            amountUnitDAO.addAmountUnit(amount_unit_name);
            amountUnit = amountUnitDAO.getAmountUnitByName(amount_unit_name);
        }
        return addIngredient(r_id, product.getId(), amount, amountUnit.getId());
    }
    public int deleteIngredientByRecipeId(int id) throws SQLException {
        String q = "delete from r_ingredients where r_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate();
    }

}
