package dev.dipeshshrestha.moviesApi.services;

import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.documents.Review;
import dev.dipeshshrestha.moviesApi.exceptions.MovieNotFoundException;
import dev.dipeshshrestha.moviesApi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    /**
     * Although Repositories helps us to interact with database and perform certain operations quickly
     * but repositories may not be able to handle complex query so we can make use of Template
     * (in this case "MongoTemplate") that helps us to easily interact with database and perform
     * complex operations.
     */
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(Review review, String movieImdbId) {
        // check if movieImdbId passed by client is correct
        boolean isMoviePresent = mongoTemplate.exists(
                Query.query(Criteria.where("imdbId").is(movieImdbId)),
                Movie.class
        );
        if(isMoviePresent){
            Review newReview = reviewRepository.insert(review);
            /**
             * Here we need to update the Movie collection with newly created review
             * so using repository it is alitlle difficult so we are using MongoTemplate
             */
            mongoTemplate.update(Movie.class)
                    .matching(Criteria.where("imdbId").is(movieImdbId))
                    .apply(new Update().push("reviewIds",review))
                    .first(); // this make sure only first entry is updated.
            return newReview;
        }
        else throw new MovieNotFoundException("Movie with imdb id as: "+movieImdbId+" not found.");
    }
}
