package com.recipehunter.dao;

import com.recipehunter.entities.AmountUnit;
import com.recipehunter.entities.Product;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmountUnitDAO {
    private Connection connection = ConnectionToDatabase.getConnection();
    public List<AmountUnit> getAllAmountUnit() throws SQLException {
        List<AmountUnit> products = new ArrayList<>();
        String q = "select * from `r_amount_units`";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            products.add(new AmountUnit(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return products;
    }
    public AmountUnit getAmountUnitByName(String name) throws SQLException {
        AmountUnit product = null;
        String q = "select * from `r_amount_units` where `name` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            product = new AmountUnit(resultSet.getInt("id"), resultSet.getString("name"));
        }
        return product;
    }
    public int addAmountUnit(String name) throws SQLException {
        String q = "insert into `r_amount_units` (`name`) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate();

    }
}
