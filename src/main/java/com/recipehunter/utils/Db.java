package com.recipehunter.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String host = "localhost";
        int port = 3306;
        String name = "recipe_hunter_db";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url,"root","rzqtgT8xA325IKeW");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
