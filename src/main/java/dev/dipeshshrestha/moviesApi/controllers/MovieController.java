package dev.dipeshshrestha.moviesApi.controllers;

import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving single movie using imdb id of movie
     * @param imdbId retrieved as a Path variable in HTTP request
     * @return Movie
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getSingleMovie(@PathVariable("id") String imdbId){
        return new ResponseEntity<>(movieService.getSingleMovie(imdbId), HttpStatus.OK);
    }
}
