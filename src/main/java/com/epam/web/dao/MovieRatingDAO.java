package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.MovieRating;

public class MovieRatingDAO extends AbstractDAO<MovieRating> {
    public MovieRatingDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean add(MovieRating movieRating) {
        boolean success = false;
        return success;
    }
}
