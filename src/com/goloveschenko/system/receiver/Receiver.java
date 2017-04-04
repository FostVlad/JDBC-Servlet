package com.goloveschenko.system.receiver;

import com.goloveschenko.resourse.ConfigurationManager;
import com.goloveschenko.system.command.TypeCommand;

public class Receiver {
    private String page;

    public Receiver() {
    }

    public String acion(TypeCommand tp){
        switch (tp){
            case LOGIN:
                page = ConfigurationManager.getProperty("path.page.main");
                break;
            case LOGOUT:
                page = ConfigurationManager.getProperty("path.page.login");
                break;
            case REGISTRATION:
                page = ConfigurationManager.getProperty("path.page.main");
                break;
            case ADD_PRODUCT:
                page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
