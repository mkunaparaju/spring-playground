package com.example.springplayground.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieSearchResponse {

    @JsonProperty("Search")
    List<Movie> search;

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
