package com.epam.web.command.factory.impl.user;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.MovieRating;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ValidationException;
import com.epam.web.service.MovieRatingService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.memento.Memento;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateMovieRatingCommand implements ActionCommand {

    private static final String ID_PARAM = "movieRatingId";
    private static final String RATING_PARAM = "rating";
    private static final String MOVIE_ID_PARAM = "movieId";
    private static final String USER_ID_PARAM = "userId";
    private static final String BASE_PAGE_PATH = "path.page.base";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieRatingService movieRatingService = new MovieRatingService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            movieRatingService.update(convertToMovieRating(requestContent));
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ValidationException | ServiceException | InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private MovieRating convertToMovieRating(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        MovieRating movieRating = new MovieRating();
        movieRating.setId(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
        movieRating.setMovieId(Integer.valueOf(requestContent.getParameter(MOVIE_ID_PARAM)));
        movieRating.setUserId(Integer.valueOf(requestContent.getParameter(USER_ID_PARAM)));
        movieRating.setRate(Float.valueOf(requestContent.getParameter(RATING_PARAM)));
        return movieRating;
    }
}
