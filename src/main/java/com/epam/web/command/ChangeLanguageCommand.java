package com.epam.web.command;

import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

public class ChangeLanguageCommand implements ActionCommand {

    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "locale";

    public String execute(SessionRequestContent requestContent) {
        String locale = requestContent.getParameter(LANGUAGE_PARAM);
        requestContent.setSessionAttribute(LOCALE_ATTR, locale);
        Memento memento = Memento.getInstance();
        return memento.getPreviousPage();
    }
}
