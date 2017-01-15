package com.epam.web.entity;

import com.epam.web.entity.type.GenderType;
import com.epam.web.entity.type.OccupationType;

import java.time.LocalDate;
import java.util.List;

public class MediaPerson extends Entity {
    private int id;
    private String firstName;
    private String lastName;
    private String bio;
    private List<OccupationType> occupation;
    private GenderType gender;
    private LocalDate birthday;
    private String picture;
    private boolean isDeleted;

    public MediaPerson() {

    }

    public MediaPerson(int id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<OccupationType> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<OccupationType> occupation) {
        this.occupation = occupation;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "MediaPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
