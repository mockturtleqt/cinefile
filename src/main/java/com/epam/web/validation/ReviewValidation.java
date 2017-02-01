package com.epam.web.validation;

import com.epam.web.entity.Review;

public class ReviewValidation implements Validation<Review> {

    public boolean isValid(Review review) {
        return review.getTitle() != null &&
                review.getBody() != null;
    }
}
