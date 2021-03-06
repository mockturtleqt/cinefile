package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.util.memento.Memento;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLanguageCommand implements ActionCommand {
    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "locale";

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            String locale = requestContent.getParameter(LANGUAGE_PARAM);
            requestContent.setSessionAttribute(LOCALE_ATTR, locale);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
