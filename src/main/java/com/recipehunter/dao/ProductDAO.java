package com.recipehunter.dao;

import com.recipehunter.entities.Product;
import com.recipehunter.utils.ConnectionToDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection = ConnectionToDatabase.getConnection();

    public List<Product> getAllPrducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String q = "select * from `r_products`";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            products.add(new Product(resultSet.getInt("id"), resultSet.getString("name")));
        }
        return products;
    }
    public Product getProductByName(String name) throws SQLException {
        Product product = null;
        String q = "select * from `r_products` where `name` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            product = new Product(resultSet.getInt("id"), resultSet.getString("name"));
        }
        return product;
    }
    public int addProduct(String name) throws SQLException {
        String q = "insert into `r_products` (`name`) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(q);
        preparedStatement.setString(1, name);
        return preparedStatement.executeUpdate();

    }
}
