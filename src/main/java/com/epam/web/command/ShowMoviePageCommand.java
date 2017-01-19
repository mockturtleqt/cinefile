package com.epam.web.command;

import com.epam.web.entity.Movie;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShowMoviePageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String MOVIE_PAGE_PATH = "path.page.movie";
    private static final String ID = "id";

    @Override
    public String execute(SessionRequestContent content) {
        String page = null;

        try {
            int id = Integer.valueOf(content.getParameter(ID));
            MovieService movieService = new MovieService();
            Movie movie = movieService.findById(id);
            content.setAttribute("moviePage", movie);
            page = ConfigurationManager.getProperty(MOVIE_PAGE_PATH);

        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }

        return page;
    }
}
