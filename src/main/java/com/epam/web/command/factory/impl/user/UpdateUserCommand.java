package com.epam.web.command.factory.impl.user;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.util.memento.Memento;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;

        Memento memento = Memento.getInstance();
        page = memento.getPreviousPage();
        return page;
    }
}
