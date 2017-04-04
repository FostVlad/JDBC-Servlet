package com.goloveschenko.system.factory;

import com.goloveschenko.system.command.*;
import com.goloveschenko.system.receiver.Receiver;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private Receiver receiver;

    public ActionFactory(Receiver receiver) {
        this.receiver = receiver;
    }

    public ICommand readCommand(HttpServletRequest request){
        ICommand current = null;
        String command = request.getParameter("command");
        TypeCommand tp = TypeCommand.valueOf(command.toUpperCase());
        switch (tp){
            case LOGIN:
                current = new LoginCommand(receiver);
                current.setRequest(request);
                break;
            case LOGOUT:
                current = new LogoutCommand(receiver);
                current.setRequest(request);
                break;
            case REGISTRATION:
                current = new RegistrationCommand(receiver);
                current.setRequest(request);
                break;
            case ADD_PRODUCT:
                current = new AddProductCommand(receiver);
                current.setRequest(request);
        }
        return current;
    }
}
