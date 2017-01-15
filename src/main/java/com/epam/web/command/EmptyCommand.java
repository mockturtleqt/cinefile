package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = ConfigurationManager.getProperty("path.page.error");
        return page;
    }
}
