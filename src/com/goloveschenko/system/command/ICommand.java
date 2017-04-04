package com.goloveschenko.system.command;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    void setRequest(HttpServletRequest request);
    String execute();
}
