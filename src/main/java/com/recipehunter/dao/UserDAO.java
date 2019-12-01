package com.recipehunter.dao;

import com.recipehunter.entities.User;
import com.recipehunter.entities.UserRole;
import com.recipehunter.utils.ConnectionToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection = ConnectionToDatabase.getConnection();

    public User getUserById(int userId) throws SQLException {
        String sqlQuery = "SELECT * FROM users WHERE id = ?";
        PreparedStatement getUserByIdStatement = connection.prepareStatement(sqlQuery);
        getUserByIdStatement.setInt(1, userId);
        ResultSet resultSet = getUserByIdStatement.executeQuery();
        return getUserByResultSet(resultSet);
    }

    public User getUserByEmail(String userEmail) throws SQLException {
        String sqlQuery = "SELECT * FROM users WHERE email = ?";
        PreparedStatement getUserByIdStatement = connection.prepareStatement(sqlQuery);
        getUserByIdStatement.setString(1, userEmail);
        ResultSet resultSet = getUserByIdStatement.executeQuery();
        return getUserByResultSet(resultSet);
    }

    public int addUser(String name, String email, String md5HexPassword, String role, String salt) throws SQLException {
        String sqlQuery = "INSERT INTO users (name, email, password, role, salt) values( ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, md5HexPassword);
        preparedStatement.setString(4, role);
        preparedStatement.setString(5, salt);
        return preparedStatement.executeUpdate();
    }

    private static User getUserByResultSet(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new User(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("role"),
                resultSet.getString("salt")
        );
    }

    public int updateUserPassword(int userId, String newPass) throws SQLException {
        String sqlQuery = "update users set `password` = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, newPass);
        preparedStatement.setInt(2, userId);
        return preparedStatement.executeUpdate();


    }


}
