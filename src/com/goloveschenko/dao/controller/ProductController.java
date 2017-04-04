package com.goloveschenko.dao.controller;

import com.goloveschenko.entities.purchases.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductController extends AbstractController {
    public static final String SELECT_ALL_QUERY = "SELECT * FROM product;";
    public static final String INSERT_QUERY = "INSERT INTO product VALUES (?,?,?);";

    public static final int ID_POS = 1;
    public static final int NAME_POS = 2;
    public static final int PRICE_POS = 3;

    public ProductController() {
    }

    public ArrayList<Product> getAllProducts(){
        Statement statement = null;
        ArrayList<Product> allProducts = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong(ID_POS));
                product.setName(resultSet.getString(NAME_POS));
                product.setPrice(resultSet.getLong(PRICE_POS));
                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return allProducts;
    }

    public void addProduct(Product product){
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(INSERT_QUERY);
            prepStatement.setLong(ID_POS, product.getId());
            prepStatement.setString(NAME_POS, product.getName());
            prepStatement.setLong(PRICE_POS, product.getPrice());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(prepStatement);
        }
    }

    public Product getProductByID(long id){
        Product product = null;
        Statement statement = null;
        String query = String.format("SELECT * FROM product WHERE ID = '%s'", id);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong(ID_POS));
                product.setName(resultSet.getString(NAME_POS));
                product.setPrice(resultSet.getLong(PRICE_POS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return product;
    }
}
