package com.goloveschenko.servlet;

import com.goloveschenko.resourse.ConfigurationManager;
import com.goloveschenko.system.factory.ActionFactory;
import com.goloveschenko.system.command.ICommand;
import com.goloveschenko.system.receiver.Receiver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/controller")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;

        Receiver receiver = new Receiver();
        ActionFactory factory = new ActionFactory(receiver);
        ICommand command = factory.readCommand(req);
        page = command.execute();

        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            req.getRequestDispatcher(page).forward(req, resp);
        }

    }
}
