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

public class UpdateReviewCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            String title = requestContent.getParameter("review-title-input");
            String body = requestContent.getParameter("review-body-input");
            int id = Integer.valueOf(requestContent.getParameter("reviewId"));

            Review review = new Review();
            review.setTitle(title);
            review.setBody(body);
            review.setId(id);
            review.setDate(LocalDate.now());

            ReviewService reviewService = new ReviewService();
            reviewService.update(review);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
