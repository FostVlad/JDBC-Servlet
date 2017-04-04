package com.goloveschenko.dao.controller;

import com.goloveschenko.entities.person.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserController extends AbstractController {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user;";
    private static final String INSERT_QUERY = "INSERT INTO user VALUES (?,?,?);";

    private static final int ID_POS = 1;
    private static final int EMAIL_POS = 2;
    private static final int PASSWORD_POS = 3;

    public UserController() {
    }

    public ArrayList<User> getAllUsers(){
        Statement statement = null;
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(ID_POS));
                user.setEmail(resultSet.getString(EMAIL_POS));
                user.setPassword(resultSet.getString(PASSWORD_POS));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return allUsers;
    }

    public User getUserByName(String name){
        User user = null;
        Statement statement = null;
        String query = String.format("SELECT * FROM user WHERE EMAIL = '%s'", name);
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(ID_POS));
                user.setEmail(resultSet.getString(EMAIL_POS));
                user.setPassword(resultSet.getString(PASSWORD_POS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return user;
    }

    public void addUser(User user){
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(INSERT_QUERY);
            prepStatement.setLong(ID_POS, user.getId());
            prepStatement.setString(EMAIL_POS, user.getEmail());
            prepStatement.setString(PASSWORD_POS, user.getPassword());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(prepStatement);
        }
    }
}
