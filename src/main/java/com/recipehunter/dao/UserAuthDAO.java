package com.recipehunter.dao;

import com.recipehunter.entities.UserAuth;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthDAO {
    private Connection connection = ConnectionToDatabase.getConnection();

    public UserAuth getUserAuthBySelector(String selector) throws SQLException {
        String sqlQuery = "SELECT * FROM users_auth WHERE selector = ?";
        PreparedStatement getUserAuth = connection.prepareStatement(sqlQuery);
        getUserAuth.setString(1, selector);
        ResultSet resultSet = getUserAuth.executeQuery();
        UserAuth userAuth = null;
        while (resultSet.next()) {
            userAuth = new UserAuth(resultSet.getString("selector"),
                    resultSet.getString("validator"),
                    resultSet.getInt("user_id"));

        }

        return userAuth;

    }

    public int delete(int userId) throws SQLException {
        String sqlQuery = "DELETE FROM users_auth WHERE user_id = ?";
        PreparedStatement getUserAuth = connection.prepareStatement(sqlQuery);
        getUserAuth.setInt(1, userId);
        return getUserAuth.executeUpdate();
    }

    public int addAuth(String selector, String md5HexValidator, int userId) throws SQLException {
        String sqlQuery = "INSERT INTO users_auth (selector, validator, user_id) VALUES (?, ?, ?) ";
        PreparedStatement getUserAuth = connection.prepareStatement(sqlQuery);
        getUserAuth.setString(1, selector);
        getUserAuth.setString(2, md5HexValidator);
        getUserAuth.setInt(3, userId);
        return getUserAuth.executeUpdate();
    }

    //TODO: Update Auth
    /*public int updateAuth(String selector, String md5HexValidator, int userId){

    }*/

}
