package com.epam.web.command;

import com.epam.web.dao.MovieDAO;
import com.epam.web.entity.Movie;
import com.epam.web.jdbc.ConnectionPool;
import com.epam.web.jdbc.DataBaseHelper;
import com.epam.web.resource.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public class FindCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    public String execute(HttpServletRequest request) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);
            String movieToFind = request.getParameter("movie-to-find");
            List<Movie> movieList = movieDAO.findMovie(movieToFind);
            request.setAttribute("queryName", "Results for " + movieToFind + ":");
            request.setAttribute("movie", movieList);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }

        return ConfigurationManager.getProperty("path.page.base");
    }
}
