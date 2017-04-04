package com.goloveschenko.system.logic;

import com.goloveschenko.dao.controller.UserController;
import com.goloveschenko.entities.person.User;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass){
        UserController userController = new UserController();
        User user = userController.getUserByName(enterLogin);
        Boolean check = false;
        if (user != null && user.getPassword().equals(enterPass)){
            check = true;
        }
        userController.closeConnection();
        return check;
    }
}
