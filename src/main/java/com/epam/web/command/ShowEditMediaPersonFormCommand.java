package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowEditMediaPersonFormCommand implements ActionCommand {
    private static final String EDIT_MEDIA_PERSON_FORM_PATH = "path.page.edit.media.person";
    private static final String ID = "mediaPersonId";
    private static final String MEDIA_PERSON_ATTR = "mediaPerson";

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        try {
            String idParam = requestContent.getParameter(ID);
            int id = Integer.valueOf(idParam);
            MediaPersonService mediaPersonService = new MediaPersonService();
            MediaPerson mediaPerson = mediaPersonService.findById(id);
            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPerson);
        } catch (NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e);
        }
        return ConfigurationManager.getProperty(EDIT_MEDIA_PERSON_FORM_PATH);
    }
}
