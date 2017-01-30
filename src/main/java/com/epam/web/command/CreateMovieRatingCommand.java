package com.epam.web.command;

import com.epam.web.entity.MovieRating;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieRatingService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateMovieRatingCommand implements ActionCommand {

    private static final String RATING_PARAM = "rating";
    private static final String MOVIE_ID_PARAM = "movieId";
    private static final String USER_ID_PARAM = "userId";
    private static final String BASE_PAGE_PATH = "path.page.base";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private MovieRatingService movieRatingService = new MovieRatingService();

    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            movieRatingService.create(this.convertToMovieRating(requestContent));

            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException | ValidationException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }

        return page;
    }

    private MovieRating convertToMovieRating(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        float rating = Float.valueOf(requestContent.getParameter(RATING_PARAM));
        int movieId = Integer.valueOf(requestContent.getParameter(MOVIE_ID_PARAM));
        int userId = Integer.valueOf(requestContent.getParameter(USER_ID_PARAM));

        MovieRating movieRating = new MovieRating();
        movieRating.setMovieId(movieId);
        movieRating.setUserId(userId);
        movieRating.setRate(rating);

        return movieRating;
    }
}
