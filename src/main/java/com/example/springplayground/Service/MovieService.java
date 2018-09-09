package com.example.springplayground.Service;

import com.example.springplayground.Config.MovieConfig;
import com.example.springplayground.Model.Movie;
import com.example.springplayground.Model.MovieSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate = new RestTemplate();
    private MovieConfig movieConfig;

    public MovieService(MovieConfig movieConfig) { this.movieConfig = movieConfig;    }

    public List<Movie> getMovies(String q) {

        URI targetUrl= UriComponentsBuilder.fromUriString(movieConfig.getHost())
                .path("/")
                .queryParam("apikey", movieConfig.getApiKey())
                .queryParam("s", q)
                .build()
                .encode()
                .toUri();

        System.out.println(targetUrl.toString());

        RequestEntity request = RequestEntity
                .get(targetUrl)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<MovieSearchResponse> response = restTemplate.getForEntity(request.getUrl(), MovieSearchResponse.class);

        return response.getBody().getSearch();
    }
}
