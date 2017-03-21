package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.util.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }
}
