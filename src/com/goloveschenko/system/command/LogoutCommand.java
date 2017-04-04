package com.goloveschenko.system.command;

import com.goloveschenko.system.receiver.Receiver;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ICommand {
    private Receiver receiver;
    private HttpServletRequest request;

    public LogoutCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        request.getSession().invalidate();
        return receiver.acion(TypeCommand.LOGOUT);
    }
}
