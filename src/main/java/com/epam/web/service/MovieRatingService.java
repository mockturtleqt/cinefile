package com.epam.web.service;

import com.epam.web.dao.MovieRatingDAO;
import com.epam.web.dao.ReviewDAO;
import com.epam.web.dao.UserDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;
import com.epam.web.entity.Review;
import com.sun.xml.internal.bind.v2.TODO;

public class MovieRatingService extends AbstractService<MovieRating> {

    public boolean create(MovieRating movieRating) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            MovieRatingDAO movieRatingDAO = new MovieRatingDAO(connection);
            UserDAO user = new UserDAO(connection);
            if (!user.hasRatedThisMovie(movieRating.getMovieId(), movieRating.getUserId())) {
                movieRatingDAO.create(movieRating);
            } else {
                movieRatingDAO.update(movieRating);
                //this.update(movieRating);
                // TODO FIND A BETTER SOLUTION
            }

            this.updateUserRating(connection, movieRating.getMovieId());
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
            this.updateUserRating(connection, movieRating.getMovieId());
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
            movieRatingDAO.deleteById(id);
            MovieRating movieRating = movieRatingDAO.findById(id);
            this.updateUserRating(connection, movieRating.getMovieId());
            success = true;
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    private boolean updateUserRating(ProxyConnection connection, int movieId) {
        boolean success;
        UserDAO userDAO = new UserDAO(connection);
        success = userDAO.updateUserRating(movieId);
        return success;
    }
}
