package dev.dipeshshrestha.moviesApi.services;

import dev.dipeshshrestha.moviesApi.controllers.MovieController;
import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
