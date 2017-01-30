package com.epam.web.service;

import com.epam.web.dao.ReviewDAO;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.resource.MessageManager;
import com.epam.web.validation.ReviewValidation;

public class ReviewService extends AbstractService<Review> {
    private static final String CREATE_REVIEW_ERROR_MSG = "msg.create.review.error";
    private static final String FIND_REVIEW_ERROR_MSG = "msg.find.review.error";
    private static final String DELETE_REVIEW_ERROR_MSG = "msg.delete.review.error";
    private static final String UPDATE_REVIEW_ERROR_MSG = "msg.update.review.error";
    private ReviewValidation reviewValidation = new ReviewValidation();

    public boolean create(Review review) throws ServiceException, InterruptedException, ValidationException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);

            if (reviewValidation.isValid(review)) {
                success = reviewDAO.create(review);
            } else {
                throw new ValidationException(MessageManager.getProperty(CREATE_REVIEW_ERROR_MSG));
            }
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_REVIEW_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public Review findById(int id) throws ServiceException, InterruptedException {
        ProxyConnection connection = null;
        Review review = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            review = reviewDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_REVIEW_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return review;
    }

    public boolean update(Review review) throws ServiceException, InterruptedException, ValidationException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);

            if (reviewValidation.isValid(review)) {
                success = reviewDAO.update(review);
            } else {
                throw new ValidationException(MessageManager.getProperty(UPDATE_REVIEW_ERROR_MSG));
            }
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_REVIEW_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        boolean success = false;
        ProxyConnection connection = null;
        try {
            connection = super.getConnection();
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            success = reviewDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_REVIEW_ERROR_MSG), e);
        } finally {
            super.returnConnection(connection);
        }
        return success;
    }
}
