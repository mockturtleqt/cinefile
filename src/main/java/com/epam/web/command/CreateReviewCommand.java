package com.epam.web.command;

import com.epam.web.entity.Review;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
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

    private static final Logger logger = LogManager.getLogger();

    private ReviewService reviewService = new ReviewService();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            reviewService.create(convertToReview(requestContent));
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
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
