package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ChangeLanguageCommand implements ActionCommand {
    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "locale";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    public String execute(SessionRequestContent requestContent) {
        String[] locale = requestContent.getRequestParameters().get(LANGUAGE_PARAM);
        Map<String, Object> sessionAttributes = requestContent.getSessionAttributes();
        if (locale != null) {
            sessionAttributes.put(LOCALE_ATTR, locale[0]);
            requestContent.setSessionAttributes(sessionAttributes);
        }
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
