package com.epam.web.service;

import com.epam.web.dao.MediaPersonDAO;
import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;
import com.epam.web.exception.NoSuchPageException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MovieService extends AbstractService<Movie> {
    private static final Logger logger = LogManager.getLogger();

    public boolean add(Movie movie) {
        return true;
    }

    public Movie findById(int id) throws InterruptedException, NoSuchPageException {
        ProxyConnection connection = null;
        Movie movie = null;
        try {
            connection = super.getConnection();
            MovieDAO movieDAO = new MovieDAO(connection);
            movie = movieDAO.findById(id);

            if (movie != null) {
                MediaPersonDAO mediaPersonDAO = new MediaPersonDAO(connection);
                movie.setCrew(mediaPersonDAO.findByMovieId(id));

                ReviewDAO reviewDAO = new ReviewDAO(connection);
                movie.setReviews(reviewDAO.findByMovieId(id));
            } else {
                throw new NoSuchPageException();
            }

        } finally {
            super.returnConnection(connection);
        }
        return movie;
    }

    public List<Movie> findAll(String title) throws InterruptedException {
        List<Movie> movies = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MovieDAO movieDAO = new MovieDAO(connection);
            movies = movieDAO.findAll(title);
        } finally {
            super.returnConnection(connection);
        }
        return movies;
    }

    public List<Movie> findTopMovies() throws InterruptedException {
        List<Movie> movies = new ArrayList<>();
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();

            MovieDAO movieDAO = new MovieDAO(connection);
            movies = movieDAO.findTopMovies();
        } finally {
            super.returnConnection(connection);
        }
        return movies;
    }
}
