// src/main/java/com/real/interview/model/dto/MovieDto.java

package com.real.interview.model.dto;

public class MovieDto {

    private Long id;
    private String title; // <-- This field needs to exist
    private Integer releaseYear; // <-- This field needs to exist

    // Default constructor (often needed for JSON deserialization)
    public MovieDto() {
    }

    // All-args constructor (useful for creating DTOs easily)
    public MovieDto(Long id, String title, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    // Getters and Setters for all fields (crucial for your service layer and API)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { // <-- ADD THIS METHOD
        return title;
    }

    public void setTitle(String title) { // <-- ADD THIS METHOD
        this.title = title;
    }

    public Integer getReleaseYear() { // <-- ENSURE THIS METHOD EXISTS
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) { // <-- ENSURE THIS METHOD EXISTS
        this.releaseYear = releaseYear;
    }

    // Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }
}