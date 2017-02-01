package com.epam.web.service;

import com.epam.web.dao.ReviewDAO;
import com.epam.web.dbConnection.ConnectionPool;
import com.epam.web.dbConnection.ProxyConnection;
import com.epam.web.entity.Review;
import com.epam.web.exception.DAOException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.resource.MessageManager;
import com.epam.web.validation.ReviewValidation;

import java.sql.SQLException;

public class ReviewService extends AbstractService<Review> {
    private static final String CREATE_REVIEW_ERROR_MSG = "msg.create.review.error";
    private static final String FIND_REVIEW_ERROR_MSG = "msg.find.review.error";
    private static final String DELETE_REVIEW_ERROR_MSG = "msg.delete.review.error";
    private static final String UPDATE_REVIEW_ERROR_MSG = "msg.update.review.error";
    private ReviewValidation reviewValidation = new ReviewValidation();

    public Review create(Review review) throws ServiceException, InterruptedException, ValidationException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            Review userReview = new Review();
            if (reviewValidation.isValid(review)) {
                userReview = reviewDAO.create(review);
            } else {
                userReview.setValidationExceptions(reviewValidation.getValidationExceptions());
            }
            return userReview;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_REVIEW_ERROR_MSG), e);
        }
    }

    public Review findById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            return reviewDAO.findById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_REVIEW_ERROR_MSG), e);
        }
    }

    public Review update(Review review) throws ServiceException, InterruptedException, ValidationException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            Review userReview = new Review();
            if (reviewValidation.isValid(review)) {
                userReview = reviewDAO.update(review);
            } else {
                userReview.setValidationExceptions(reviewValidation.getValidationExceptions());
            }
            return userReview;
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_REVIEW_ERROR_MSG), e);
        }
    }

    public boolean deleteById(int id) throws ServiceException, InterruptedException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            ReviewDAO reviewDAO = new ReviewDAO(connection);
            return reviewDAO.deleteById(id);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_REVIEW_ERROR_MSG), e);
        }
    }

//    private boolean isValid(Review review) throws ValidationException {
//        if (reviewValidation.isValid(review)) {
//            return true;
//        } else {
//            throw new ValidationException(MessageManager.getProperty(CREATE_REVIEW_ERROR_MSG));
//        }
//    }
}
