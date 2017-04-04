package com.goloveschenko.system.command;

import com.goloveschenko.dao.controller.ProductController;
import com.goloveschenko.resourse.ConfigurationManager;
import com.goloveschenko.resourse.MessageManager;
import com.goloveschenko.system.logic.RegistrationLogic;
import com.goloveschenko.system.receiver.Receiver;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ICommand {
    private static final String PARAM_NAME_LOGIN = "loginParam";
    private static final String PARAM_NAME_PASSWORD = "passwordParam";
    private Receiver receiver;
    private HttpServletRequest request;

    public RegistrationCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String page;
        if (RegistrationLogic.checkNotLogin(login, pass)){
            request.setAttribute("user", login);
            ProductController prodController = new ProductController();
            request.setAttribute("allProducts", prodController.getAllProducts());
            prodController.closeConnection();
            page = receiver.acion(TypeCommand.REGISTRATION);
        } else {
            request.setAttribute("errorCheckLogin", MessageManager.getProperty("message.registrationerror"));
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }
}
