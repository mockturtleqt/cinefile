package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = ConfigurationManager.getProperty("path.page.index");
        //request.getSession().invalidate();
        return page;
    }
}
