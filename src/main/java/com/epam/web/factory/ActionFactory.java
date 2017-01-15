package com.epam.web.factory;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandEnum;
import com.epam.web.command.EmptyCommand;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionFactory {
    private static final String COMMAND = "command";
    private static final String WRONG_ACTION = "wrongAction";
    private static final String WRONG_ACTION_MSG = "message.wrongaction";
    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String[] action = requestContent.getRequestParameters().get(COMMAND);
        if (action == null || action[0].isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action[0].toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            Map<String, Object> requestAttributes = requestContent.getRequestAttributes();
            requestAttributes.put(WRONG_ACTION, action[0] + MessageManager.getProperty(WRONG_ACTION_MSG));
            requestContent.setRequestAttributes(requestAttributes);
        }
        return current;
    }
}
