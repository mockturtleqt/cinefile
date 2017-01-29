package com.epam.web.command;

import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String USER = "user";

    @Override
    public String execute(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(USER);
        Memento memento = Memento.getInstance();
        return memento.getPreviousPage();
    }
}
