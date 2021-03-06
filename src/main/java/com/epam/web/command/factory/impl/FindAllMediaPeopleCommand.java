package com.epam.web.command.factory.impl;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.MediaPerson;
import com.epam.web.service.MediaPersonService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllMediaPeopleCommand implements ActionCommand {
    private static final String MEDIA_PERSON_ATTR = "mediaPerson";
    private static final String SORTED_MEDIA_PEOPLE_PAGE_PATH = "path.page.sorted.media.people";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MediaPersonService mediaPersonService = new MediaPersonService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<MediaPerson> mediaPeople = mediaPersonService.findAll();
            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPeople);
            page = ConfigurationManager.getProperty(SORTED_MEDIA_PEOPLE_PAGE_PATH);
        } catch (ServiceException | InterruptedException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
