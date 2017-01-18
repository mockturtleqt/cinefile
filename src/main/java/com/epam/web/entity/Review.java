package com.epam.web.entity;

public class Review extends Entity {
    private int id;
    private int userId;
    private int movieId;
    private String title;
    private String body;
    private boolean isDeleted;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Review(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
