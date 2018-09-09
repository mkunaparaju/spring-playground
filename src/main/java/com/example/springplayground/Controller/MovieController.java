package com.example.springplayground.Controller;

import com.example.springplayground.Model.Movie;
import com.example.springplayground.Service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies(@RequestParam String q){
        return movieService.getMovies(q);
    }
}
