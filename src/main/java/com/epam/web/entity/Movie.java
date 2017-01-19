package com.epam.web.entity;

import com.epam.web.entity.type.GenreType;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Date;
import java.util.List;

public class Movie extends Entity {
    private int id;
    private String title;
    private LocalTime duration;
    private Date releaseDate;
    private String description;
    private String poster;
    private float rating;
    private List<GenreType> genre;
    private boolean isDeleted;
    private List<Review> reviews;
    private List<MediaPerson> crew;

    public List<MediaPerson> getCrew() {
        return crew;
    }

    public void setCrew(List<MediaPerson> crew) {
        this.crew = crew;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<GenreType> getGenre() {
        return genre;
    }

    public void setGenre(List<GenreType> genre) {
        this.genre = genre;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }
}
