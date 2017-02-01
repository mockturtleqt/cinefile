package com.epam.web.validation;

import com.epam.web.entity.MovieRating;
import com.epam.web.exception.ValidationException;

public class MovieRatingValidation implements Validation<MovieRating> {

    public boolean isValid(MovieRating movieRating) {
        return movieRating.getUserId() != 0 &&
                movieRating.getMovieId() != 0 &&
                movieRating.getRate() != 0.0f;
    }

//    private boolean isUserIdValid(int userId) {
//        if (userId != 0) {
//            return true;
//        } else {
//            throw ValidationException("User id is not valid");
//        }
//    }
}
