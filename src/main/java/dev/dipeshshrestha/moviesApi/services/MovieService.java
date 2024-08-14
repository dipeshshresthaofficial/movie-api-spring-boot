package dev.dipeshshrestha.moviesApi.services;

import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.exceptions.MovieNotFoundException;
import dev.dipeshshrestha.moviesApi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    /**
     * Retrieves all movies from the database
     * @return list of Movie object
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * This method performs business logic for retrieving a movie via imdbId
     * Stores Optional object of Movie entity retrieved from repository. This value can be null if movie is not found.
     * @param imdbId is imdb id of movie passed as a parameter from controller
     * @return movie object if movie found else a custom exception using MovieNotFoundException if movie not found
     */
    public Movie getSingleMovie(String imdbId) {
        Optional<Movie> movie = movieRepository.findByImdbId(imdbId);
        return movie.orElseThrow( () -> new MovieNotFoundException("Movie with imdb id as: "+imdbId+" not found.")); // if movie is not pulled return null;
    }
}
