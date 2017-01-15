package com.epam.web.factory;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandEnum;
import com.epam.web.command.EmptyCommand;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionFactory {
    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String[] action = requestContent.getRequestParameters().get("command");
        if (action == null || action[0].isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action[0].toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            Map<String, Object> requestAttributes = requestContent.getRequestAttributes();
            requestAttributes.put("wrongAction", action[0] + MessageManager.getProperty("message.wrongaction"));
            requestContent.setRequestAttributes(requestAttributes);
        }
        return current;
    }
}
