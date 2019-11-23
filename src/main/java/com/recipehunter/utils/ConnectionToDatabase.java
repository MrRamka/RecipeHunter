package com.recipehunter.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDatabase  {
    private static volatile Connection connection = null;

    public static synchronized Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(connection == null){
                synchronized (ConnectionToDatabase.class){
                    if(connection == null){
                        String host = "localhost";
                        int port = 3306;
                        String name = "recipe_hunter_db";
                        String url = "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC";
                        connection = DriverManager.getConnection(url,"root","rzqtgT8xA325IKeW");
                    }
                }


            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
