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

public class UpdateReviewCommand implements ActionCommand {
    private static final String REVIEW_TITLE_INPUT_PARAM = "review-title-input";
    private static final String REVIEW_BODY_INPUT_PARAM = "review-body-input";
    private static final String REVIEW_ID_PARAM = "reviewId";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private static final Logger logger = LogManager.getLogger();

    private ReviewService reviewService = new ReviewService();

    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            String title = requestContent.getParameter(REVIEW_TITLE_INPUT_PARAM);
            String body = requestContent.getParameter(REVIEW_BODY_INPUT_PARAM);
            int id = Integer.valueOf(requestContent.getParameter(REVIEW_ID_PARAM));

            Review review = new Review();
            review.setTitle(title);
            review.setBody(body);
            review.setId(id);
            review.setDate(LocalDate.now());

            reviewService.update(review);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (ValidationException | ServiceException | InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
