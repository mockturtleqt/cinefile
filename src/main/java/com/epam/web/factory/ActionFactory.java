package com.epam.web.factory;

import com.epam.web.command.ActionCommand;
import com.epam.web.command.CommandEnum;
import com.epam.web.command.EmptyCommand;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.MessageManager;

public class ActionFactory {
    private static final String COMMAND_PARAM = "command";
    private static final String WRONG_ACTION_ATTR = "wrongAction";
    private static final String WRONG_ACTION_MSG = "message.wrongaction";

    public ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String action = requestContent.getParameter(COMMAND_PARAM);
        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            requestContent.setAttribute(WRONG_ACTION_ATTR, action + MessageManager.getProperty(WRONG_ACTION_MSG));
        }
        return current;
    }
}
