package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.MovieRating;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieRatingService;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RateMovieCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    private static final String RATING_PARAM = "rating";
    private static final String MOVIE_ID_PARAM = "movieId";
    private static final String USER_ID_PARAM = "userId";
    private static final String BASE_PAGE_PATH = "path.page.base";

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            MovieRatingService movieRatingService = new MovieRatingService();
            movieRatingService.create(this.convertToMovieRating(requestContent));

//            requestContent.setAttribute(MOVIE_ATTR, movieList);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
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
