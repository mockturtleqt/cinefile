package com.epam.web.command;

import com.epam.web.entity.Review;
import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class AddReviewCommand implements ActionCommand {
    private static final String REVIEW_TITLE_INPUT = "review-title-input";
    private static final String REVIEW_BODY_INPUT = "review-body-input";
    private static final String MOVIE_ID = "movie-id";
    private static final String USER_ID = "user-id";
    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            String title = requestContent.getParameter(REVIEW_TITLE_INPUT);
            String body = requestContent.getParameter(REVIEW_BODY_INPUT);
            int movieId = Integer.valueOf(requestContent.getParameter(MOVIE_ID));
            int userId = Integer.valueOf(requestContent.getParameter(USER_ID));
            Review review = new Review();
            review.setTitle(title);
            review.setBody(body);
            review.setUserId(userId);
            review.setMovieId(movieId);
            review.setDate(LocalDate.now());

            ReviewService reviewService = new ReviewService();
            reviewService.add(review);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
