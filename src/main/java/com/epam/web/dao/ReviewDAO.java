package com.epam.web.dao;

import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;

public class ReviewDAO extends AbstractDAO<Review>{
    public ReviewDAO(ProxyConnection connection) {
        super(connection);
    }

    public boolean add(Review review) {
        boolean success = false;
        return success;
    }
}
