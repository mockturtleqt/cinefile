package com.epam.web.entity;

import com.epam.web.entity.type.GenreType;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Movie extends Entity {
    private int id;
    private String title;
    private Period duration;
    private LocalDate releaseDate;
    private String description;
    private String poster;
    private float rating;
    private List<GenreType> genre;
    private boolean isDeleted;

    public Movie() {

    }

    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Period getDuration() {
        return duration;
    }

    public void setDuration(Period duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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
