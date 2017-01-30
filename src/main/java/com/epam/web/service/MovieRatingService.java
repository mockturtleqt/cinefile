package com.epam.web.service;

import com.epam.web.dao.MovieDAO;
import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Movie;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.Review;
import com.epam.web.entity.User;
import com.epam.web.trigger.MovieRatingTrigger;
import com.sun.xml.internal.bind.v2.TODO;

public class MovieRatingService extends AbstractService<MovieRating> {

    public boolean create(MovieRating movieRating) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movieRatingDAO.create(movieRating);

            this.updateUserRating(connection, movieRating);

            success = true;
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public MovieRating findById(int id) throws InterruptedException {
        MovieRating movieRating = null;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movieRating = movieRatingDAO.findById(id);
        } finally {
            super.returnConnection(connection);
        }
        return movieRating;
    }

    public boolean update(MovieRating movieRating) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            movieRatingDAO.update(movieRating);

            this.updateUserRating(connection, movieRating);
            success = true;
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            success = movieRatingDAO.deleteById(id);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    private boolean updateUserRating(ProxyConnection connection, MovieRating movieRating) {
        boolean success;
        UserDAO userDAO = new UserDAO(connection);
        User user = userDAO.findById(movieRating.getUserId());

        MovieRatingTrigger movieRatingTrigger = new MovieRatingTrigger();
        int newUserRating = movieRatingTrigger.calculateNewUserRating(user.getUserRating(),
                this.calculateMovieRatingDifference(connection, movieRating));

        success = userDAO.updateUserRating(user.getId(), newUserRating);
        return success;
    }

    private float calculateMovieRatingDifference(ProxyConnection connection, MovieRating movieRating) {
        MovieDAO movieDAO = new MovieDAO(connection);
        Movie movie = movieDAO.findById(movieRating.getMovieId());
        return Math.abs(movie.getRating() - movieRating.getRate());
    }
}
