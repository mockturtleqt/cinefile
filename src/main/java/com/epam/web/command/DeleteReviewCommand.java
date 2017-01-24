package com.epam.web.command;

import com.epam.web.exception.NoSuchRequestParameterException;
import com.epam.web.memento.Memento;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.service.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteReviewCommand implements ActionCommand {
    private static final String ID = "reviewId";
    private static final Logger logger = LogManager.getLogger();
    private ReviewService reviewService = new ReviewService();

    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {
            int id = Integer.valueOf(requestContent.getParameter(ID));
            reviewService.deleteById(id);
            Memento memento = Memento.getInstance();
            page = memento.getPreviousPage();
        } catch (InterruptedException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }

        return page;
    }
}
