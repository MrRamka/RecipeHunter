package com.recipehunter.dao;

import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stat {
    private Connection connection = ConnectionToDatabase.getConnection();

    public int getUsersAmount() throws SQLException {
        String q = "select count(id) as amount from users";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("amount");
    }
    public int getRecipesAmount() throws SQLException {
        String q = "select count(id) as amount from recipes";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("amount");
    }

}
