package dev.dipeshshrestha.moviesApi.exceptions;

/**
 * This class handles only the movie not found exception
 */
public class MovieNotFoundException extends RuntimeException{
    /**
     * This constructor initializes the messages variable of superclass.
     * @param message stores the text that explains what exactly caused the exception.
     */
    public MovieNotFoundException(String message){
        super(message);
    }
}
