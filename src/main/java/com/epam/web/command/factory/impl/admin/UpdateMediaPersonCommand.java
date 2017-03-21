package com.epam.web.command.factory.impl.admin;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.MediaPerson;
import com.epam.web.domain.type.GenderType;
import com.epam.web.domain.type.OccupationType;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MediaPersonService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateMediaPersonCommand implements ActionCommand {
    private static final String ID_PARAM = "media-person-id";
    private static final String FIRST_NAME_PARAM = "first-name";
    private static final String LAST_NAME_PARAM = "last-name";
    private static final String BIOGRAPHY_PARAM = "bio";
    private static final String OCCUPATION_PARAM = "occupation";
    private static final String GENDER_PARAM = "gender";
    private static final String BIRTHDAY_PARAM = "birthday";
    private static final String PICTURE_PARAM = "picture";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String MEDIA_PERSON_ATTR = "mediaPersonPage";
    private static final String MEDIA_PERSON_PAGE_PATH = "path.page.media.person";

    private static final Logger logger = LogManager.getLogger();

    private MediaPersonService mediaPersonService = new MediaPersonService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            MediaPerson mediaPerson = mediaPersonService.update(convertToMediaPerson(requestContent));
            requestContent.setAttribute(MEDIA_PERSON_ATTR, mediaPerson);
            page = ConfigurationManager.getProperty(MEDIA_PERSON_PAGE_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private MediaPerson convertToMediaPerson(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        MediaPerson mediaPerson = new MediaPerson();
        mediaPerson.setId(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
        mediaPerson.setFirstName(requestContent.getParameter(FIRST_NAME_PARAM));
        mediaPerson.setLastName(requestContent.getParameter(LAST_NAME_PARAM));
        mediaPerson.setBio(requestContent.getParameter(BIOGRAPHY_PARAM));
        try {
            List<OccupationType> occupationTypes = new ArrayList<>();
            for (String occupation : requestContent.getParameters(OCCUPATION_PARAM)) {
                occupationTypes.add(OccupationType.valueOf(occupation.toUpperCase()));
            }
            mediaPerson.setOccupation(occupationTypes);
            String gender = requestContent.getParameter(GENDER_PARAM);
            GenderType genderType = (gender != null) ? GenderType.valueOf(gender) : null;
            mediaPerson.setGender(genderType);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
        }

        String stringBirthday = requestContent.getParameter(BIRTHDAY_PARAM);
        LocalDate birthday = (stringBirthday != null && !stringBirthday.isEmpty()) ?
                LocalDate.parse(stringBirthday) : null;
        mediaPerson.setBirthday(birthday);
        mediaPerson.setPicture(requestContent.getParameter(PICTURE_PARAM));

        return mediaPerson;
    }
}
