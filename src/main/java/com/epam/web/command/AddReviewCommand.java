package com.epam.web.command;

import com.epam.web.entity.Review;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.service.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            String title = requestContent.getParameter("review-title-input");
            String body = requestContent.getParameter("review-body-input");
            int movieId = Integer.valueOf(requestContent.getParameter("movie-id"));
            int userId = Integer.valueOf(requestContent.getParameter("user-id"));
            Review review = new Review();
            review.setTitle(title);
            review.setBody(body);
            review.setUserId(userId);
            review.setMovieId(movieId);

            ReviewService reviewService = new ReviewService();
            reviewService.add(review);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
