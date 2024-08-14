package dev.dipeshshrestha.moviesApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  A global exception handler for handling exceptions across the entire application.
 * @ControllerAdvice allows for the central management of exceptions
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles exception when movie is not found in repository.
     *
     * Returns a custom error response to the client. It creates an object
     * with a descriptive error message and HTTP status of 404 Not Found.
     * @param ex, object of MovieNotFoundException with custom message.
     * @return ResponseEntity containing custom ErrorMessage and HTTP status
     *
     */
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleMovieNotFoundException(MovieNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage("Movie Not Found", ex.getMessage());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
