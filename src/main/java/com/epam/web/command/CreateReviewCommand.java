package com.epam.web.command;

import com.epam.web.entity.Review;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.exception.ServiceException;
import com.epam.web.exception.ValidationException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class CreateReviewCommand implements ActionCommand {
    private static final String REVIEW_TITLE_INPUT_PARAM = "review-title-input";
    private static final String REVIEW_BODY_INPUT_PARAM = "review-body-input";
    private static final String MOVIE_ID_PARAM = "movie-id";
    private static final String USER_ID_PARAM = "user-id";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String VALIDATION_EXCEPTIONS = "validationExceptions";

    private static final Logger logger = LogManager.getLogger();

    private ReviewService reviewService = new ReviewService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            Review review = reviewService.create(convertToReview(requestContent));
            if (review.getValidationExceptions() != null || !review.getValidationExceptions().isEmpty()) {
                requestContent.setAttribute(VALIDATION_EXCEPTIONS, review.getValidationExceptions());
            }
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ServiceException | InterruptedException | NoSuchRequestParameterException | ValidationException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }

    private Review convertToReview(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        Review review = new Review();
        review.setTitle(requestContent.getParameter(REVIEW_TITLE_INPUT_PARAM));
        review.setBody(requestContent.getParameter(REVIEW_BODY_INPUT_PARAM));
        review.setMovieId(Integer.valueOf(requestContent.getParameter(MOVIE_ID_PARAM)));
        review.setUserId(Integer.valueOf(requestContent.getParameter(USER_ID_PARAM)));
        review.setDate(LocalDate.now());
        return review;
    }
}
