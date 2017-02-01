package com.epam.web.command;

import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(USER_ATTR);
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
