package com.epam.web.command.factory.impl.user;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.util.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(USER_ATTR);
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
