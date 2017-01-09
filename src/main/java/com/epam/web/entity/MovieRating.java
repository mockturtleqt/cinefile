package com.epam.web.entity;

public class MovieRating {
    private int userId;
    private int movieId;
    private float rate;
    private boolean isDeleted;

    public MovieRating() {

    }

    public MovieRating(int userId, int movieId, float rate) {
        this.userId = userId;
        this.movieId = movieId;
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", rate=" + rate +
                '}';
    }
}
