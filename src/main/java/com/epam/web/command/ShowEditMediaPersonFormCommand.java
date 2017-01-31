package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.OccupationType;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowEditMediaPersonFormCommand implements ActionCommand {
    private static final String EDIT_MEDIA_PERSON_FORM_PATH = "path.page.edit.media.person";
    private static final String ID_PARAM = "mediaPersonId";
    private static final String MEDIA_PERSON_ATTR = "mediaPerson";
    private static final String OCCUPATION_TYPE_ATTR = "occupationType";
    private static final String GENDER_TYPE_ATTR = "genderType";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MediaPersonService mediaPersonService = new MediaPersonService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            requestContent.setAttribute(OCCUPATION_TYPE_ATTR, OccupationType.values());
            requestContent.setAttribute(GENDER_TYPE_ATTR, GenderType.values());
            MediaPerson mediaPerson = mediaPersonService.findById(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPerson);
            page = ConfigurationManager.getProperty(EDIT_MEDIA_PERSON_FORM_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
