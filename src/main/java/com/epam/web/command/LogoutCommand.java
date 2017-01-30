package com.epam.web.command;

import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;

public class LogoutCommand implements ActionCommand {
    private static final String USER_ATTR = "user";

    @Override
    public String execute(SessionRequestContent requestContent) {
        requestContent.removeSessionAttribute(USER_ATTR);
        Memento memento = Memento.getInstance();
        return memento.getPreviousPage();
    }
}
