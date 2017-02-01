package com.epam.web.entity;


import java.time.LocalDate;
import java.util.List;

public class Review extends Entity {
    private int id;
    private int userId;
    private int movieId;
    private String title;
    private String body;
    private boolean isDeleted;
    private LocalDate date;
    private String userLogin;
    private String movieTitle;
    private List<String> validationExceptions;

    public List<String> getValidationExceptions() {
        return validationExceptions;
    }

    public void setValidationExceptions(List<String> validationExceptions) {
        this.validationExceptions = validationExceptions;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
