package com.epam.web.command;

import com.epam.web.requestContent.SessionRequestContent;

public interface ActionCommand {

    String execute(SessionRequestContent requestContent);
}
