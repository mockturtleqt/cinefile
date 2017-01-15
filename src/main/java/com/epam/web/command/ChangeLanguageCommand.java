package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ChangeLanguageCommand implements ActionCommand {
    public String execute(SessionRequestContent requestContent) {

        String[] locale = requestContent.getRequestParameters().get("language");
        Map<String, Object> sessionAttributes = requestContent.getSessionAttributes();
        if (locale != null) {
            sessionAttributes.put("locale", locale[0]);
            requestContent.setSessionAttributes(sessionAttributes);
        }
        return ConfigurationManager.getProperty("path.page.index");
    }
}
