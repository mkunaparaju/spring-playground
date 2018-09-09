package com.example.springplayground.Model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Movie {
    @JsonSetter("Title")
    private String title;
    @JsonSetter("imdbID")
    private String imdbId;
    @JsonSetter("Poster")
    private String poster;
    @JsonSetter("Year")
    private String year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
