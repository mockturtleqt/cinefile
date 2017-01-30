package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.entity.type.GenreType;
import com.epam.web.exception.NoSuchPageException;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;

public class ShowEditMovieFormCommand implements ActionCommand {
    private static final String EDIT_MOVIE_FORM_PATH = "path.page.edit.movie";
    private static final String ID = "movieId";
    private static final String MOVIE_ATTR = "movie";
    private static final String GENRE_TYPE_ATTR = "genreType";

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        try {
            requestContent.setAttribute(GENRE_TYPE_ATTR, GenreType.values());

            String idParam = requestContent.getParameter(ID);
            int id = Integer.valueOf(idParam);
            MovieService movieService = new MovieService();
            Movie movie = movieService.findById(id);
            requestContent.setAttribute(MOVIE_ATTR, movie);
        } catch (NoSuchRequestParameterException | InterruptedException | NoSuchPageException e) {
            logger.log(Level.ERROR, e);
        }
        return ConfigurationManager.getProperty(EDIT_MOVIE_FORM_PATH);
    }
}
