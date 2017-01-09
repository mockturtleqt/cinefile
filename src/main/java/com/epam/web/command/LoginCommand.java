package com.epam.web.command;

import com.epam.web.resource.ConfigurationManager;
import com.epam.web.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        if ("mockturtle".equals(login) && "hey".equals(password)) {
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.result");
        } else {
            request.setAttribute("errorLoginPassMsg", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
