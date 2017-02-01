package com.epam.web.validation;

import com.epam.web.entity.MovieRating;
import com.epam.web.entity.Review;
import com.epam.web.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class MovieRatingValidation implements Validation<MovieRating> {

    public boolean isValid(MovieRating movieRating) {
        return movieRating.getUserId() != 0 &&
                movieRating.getMovieId() != 0 &&
                movieRating.getRate() != 0.0f;
    }

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
