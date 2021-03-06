package com.epam.web.command.factory.impl.admin;

import com.epam.web.command.ActionCommand;
import com.epam.web.controller.requestContent.SessionRequestContent;
import com.epam.web.domain.Movie;
import com.epam.web.domain.type.GenreType;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.service.MovieService;
import com.epam.web.service.exception.ServiceException;
import com.epam.web.util.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateMovieCommand implements ActionCommand {
    private static final String TITLE_PARAM = "title";
    private static final String RELEASE_DATE_PARAM = "release-date";
    private static final String DESCRIPTION_PARAM = "description";
    private static final String GENRE_PARAM = "genre";
    private static final String POSTER_PARAM = "poster";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String MOVIE_ATTR = "moviePage";
    private static final String MOVIE_PAGE_PATH = "path.page.movie";

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            Movie movie = movieService.create(convertToMovie(requestContent));
            requestContent.setAttribute(MOVIE_ATTR, movie);
            page = ConfigurationManager.getProperty(MOVIE_PAGE_PATH);
        } catch (ServiceException | NoSuchRequestParameterException | InterruptedException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private Movie convertToMovie(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        Movie movie = new Movie();
        movie.setTitle(requestContent.getParameter(TITLE_PARAM));
        String stringReleaseDate = requestContent.getParameter(RELEASE_DATE_PARAM);
        LocalDate releaseDate = (stringReleaseDate != null && !stringReleaseDate.isEmpty()) ?
                LocalDate.parse(stringReleaseDate) : null;
        movie.setReleaseDate(releaseDate);
        movie.setDescription(requestContent.getParameter(DESCRIPTION_PARAM));

        try {
            List<GenreType> genres = new ArrayList<>();
            for (String genre : requestContent.getParameters(GENRE_PARAM)) {
                genres.add(GenreType.valueOf(genre.toUpperCase()));
            }
            movie.setGenre(genres);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
        }

        movie.setPoster(requestContent.getParameter(POSTER_PARAM));
        return movie;
    }
}
