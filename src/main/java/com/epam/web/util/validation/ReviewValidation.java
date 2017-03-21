package com.epam.web.util.validation;

import com.epam.web.domain.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewValidation implements Validation<Review> {

    private List<String> validationExceptions = new ArrayList<>();

    public List<String> getValidationExceptions() {
        return validationExceptions;
    }

    public boolean isValid(Review review) {
        return isTitleValid(review.getTitle()) && isBodyValid(review.getBody());
    }

    private boolean isTitleValid(String title) {
        if (title != null && !title.isEmpty() && title.length() < 100) {
            return true;
        } else {
            validationExceptions.add("Review title is not valid.");
            return false;
        }
    }

    private boolean isBodyValid(String body) {
        if (body != null && !body.isEmpty()) {
            return true;
        } else {
            validationExceptions.add("Review body is not valid.");
            return false;
        }
    }
}
