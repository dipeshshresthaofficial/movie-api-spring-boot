package dev.dipeshshrestha.moviesApi.controllers;

import dev.dipeshshrestha.moviesApi.documents.Review;
import dev.dipeshshrestha.moviesApi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class ReviewController {

    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/{imdbId}/reviews")
    public ResponseEntity<Review> createReview(@RequestBody Review review, @PathVariable("imdbId") String movieImdbId){

        return new ResponseEntity<>(reviewService.createReview(review, movieImdbId), HttpStatus.CREATED);
    }
}
