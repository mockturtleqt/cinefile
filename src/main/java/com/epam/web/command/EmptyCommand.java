package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }
}
