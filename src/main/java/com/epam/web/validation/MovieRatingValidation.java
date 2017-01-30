package com.epam.web.validation;

import com.epam.web.entity.MovieRating;

public class MovieRatingValidation implements Validation<MovieRating> {

    public boolean isValid(MovieRating movieRating) {
        return  movieRating.getUserId() != 0 &&
                movieRating.getMovieId() != 0 &&
                movieRating.getRate() != 0.0f;
    }
}
