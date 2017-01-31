package com.epam.web.command;

import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
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
