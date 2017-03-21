package com.epam.web.command;

import com.epam.web.controller.requestContent.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}
