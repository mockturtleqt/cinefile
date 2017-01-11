package com.epam.web.command;

import com.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        //String locale = request.getParameter("locale");

        //String locale = request.getLocale().toString();
        String locale = request.getParameter("language");
        HttpSession session = request.getSession();
        session.setAttribute("locale", locale);
        //session.setAttribute("language", "ru_RU");
        return ConfigurationManager.getProperty("path.page.index");
    }
}
