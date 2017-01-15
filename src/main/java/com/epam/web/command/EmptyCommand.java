package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final String ERROR_PAGE_PATH = "page.path.error";
    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        return page;
    }
}
