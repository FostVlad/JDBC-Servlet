package com.goloveschenko.system.logic;

import com.goloveschenko.dao.controller.UserController;
import com.goloveschenko.entities.person.User;

public class RegistrationLogic {
    public static boolean checkNotLogin(String enterLogin, String enterPass){
        UserController userController = new UserController();
        User user = userController.getUserByName(enterLogin);
        Boolean check;
        if (user == null){
            user = new User();
            user.setEmail(enterLogin);
            user.setPassword(enterPass);
            userController.addUser(user);
            check = true;
        } else {
            check = false;
        }
        userController.closeConnection();
        return check;
    }
}
