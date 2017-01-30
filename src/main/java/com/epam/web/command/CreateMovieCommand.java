package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.service.MovieService;
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

    private static final Logger logger = LogManager.getLogger();

    private MovieService movieService = new MovieService();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            movieService.create(convertToMovie(requestContent));
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (NoSuchRequestParameterException | InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    private Movie convertToMovie(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        Movie movie = new Movie();
        movie.setTitle(requestContent.getParameter(TITLE_PARAM));
        String stringReleaseDate = requestContent.getParameter(RELEASE_DATE_PARAM);
        LocalDate releaseDate = (stringReleaseDate != null) ? LocalDate.parse(stringReleaseDate) : null;
        movie.setReleaseDate(releaseDate);
        movie.setDescription(requestContent.getParameter(DESCRIPTION_PARAM));
        List<GenreType> genres = new ArrayList<>();
        for (String genre : requestContent.getParameters(GENRE_PARAM)) {
            genres.add(GenreType.valueOf(genre.toUpperCase()));
        }
        movie.setGenre(genres);
        movie.setPoster(requestContent.getParameter(POSTER_PARAM));
        return movie;
    }
}
