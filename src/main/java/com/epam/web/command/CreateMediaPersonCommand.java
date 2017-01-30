package com.epam.web.command;

import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.OccupationType;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MediaPersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateMediaPersonCommand implements ActionCommand {
    private static final String FIRST_NAME_PARAM = "first-name";
    private static final String LAST_NAME_PARAM = "last-name";
    private static final String BIOGRAPHY_PARAM = "bio";
    private static final String OCCUPATION_PARAM = "occupation";
    private static final String GENDER_PARAM = "gender";
    private static final String BIRTHDAY_PARAM = "birthday";
    private static final String PICTURE_PARAM = "picture";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MediaPersonService mediaPersonService = new MediaPersonService();

    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            mediaPersonService.create(convertToMediaPerson(requestContent));
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private MediaPerson convertToMediaPerson(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        MediaPerson mediaPerson = new MediaPerson();
        mediaPerson.setFirstName(requestContent.getParameter(FIRST_NAME_PARAM));
        mediaPerson.setLastName(requestContent.getParameter(LAST_NAME_PARAM));
        mediaPerson.setBio(requestContent.getParameter(BIOGRAPHY_PARAM));
        List<OccupationType> occupationTypes = new ArrayList<>();
        for (String occupation : requestContent.getParameters(OCCUPATION_PARAM)) {
            occupationTypes.add(OccupationType.valueOf(occupation.toUpperCase()));
        }
        mediaPerson.setOccupation(occupationTypes);
        String gender = requestContent.getParameter(GENDER_PARAM);
        GenderType genderType = (gender != null) ? GenderType.valueOf(gender) : null;
        mediaPerson.setGender(genderType);
        String stringBirthday = requestContent.getParameter(BIRTHDAY_PARAM);
        LocalDate birthday = (stringBirthday != null) ? LocalDate.parse(stringBirthday) : null;
        mediaPerson.setBirthday(birthday);
        mediaPerson.setPicture(requestContent.getParameter(PICTURE_PARAM));

        return mediaPerson;
    }
}
