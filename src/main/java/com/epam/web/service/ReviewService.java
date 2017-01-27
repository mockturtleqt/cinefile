package com.epam.web.service;

import com.epam.web.dao.ReviewDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;

public class ReviewService extends AbstractService<Review> {

    public boolean create(Review review) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            success = reviewDAO.create(review);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean update(Review review) throws InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            success = reviewDAO.update(review);
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
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            success = reviewDAO.deleteById(id);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }
}
