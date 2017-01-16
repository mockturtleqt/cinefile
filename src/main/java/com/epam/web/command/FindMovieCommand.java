package com.epam.web.command;

import com.epam.web.dao.MovieDAO;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.entity.Movie;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class FindMovieCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    private static final String MOVIE_TO_FIND_PARAM = "movie-to-find";
    private static final String QUERY_NAME_ATTR = "queryName";
    private static final String MOVIE_ATTR = "movie";
    private static final String BASE_PAGE_PATH = "path.page.base";

    public String execute(SessionRequestContent requestContent) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();

            String movieToFind = requestContent.getParameter(MOVIE_TO_FIND_PARAM);

            MovieDAO movieDAO = new MovieDAO(connection);
            List<Movie> movieList = movieDAO.findMovie(movieToFind);

            requestContent.setAttribute(QUERY_NAME_ATTR, "Results for " + movieToFind + ":");
            requestContent.setAttribute(MOVIE_ATTR, movieList);

            connectionPool.closeConnection(connection);

        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return ConfigurationManager.getProperty(BASE_PAGE_PATH);
    }
}
