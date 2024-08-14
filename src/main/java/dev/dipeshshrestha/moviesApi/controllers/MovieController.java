package dev.dipeshshrestha.moviesApi.controllers;

import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    /**
     * Endpoint for retrieving single movie using imdb id of movie
     * @param imdbId retrieved as a Path variable in HTTP request
     * @return Movie
     */
    @GetMapping("/{id}")
    public Movie getSingleMovie(@PathVariable("id") String imdbId){
        return movieService.getSingleMovie(imdbId);
    }
}
