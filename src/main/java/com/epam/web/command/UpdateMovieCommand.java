package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateMovieCommand implements ActionCommand {
    private static final String ID_PARAM = "movie-id";
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
            Movie movie = movieService.update(convertToMovie(requestContent));
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
        movie.setId(Integer.valueOf(requestContent.getParameter(ID_PARAM)));
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
