package dev.dipeshshrestha.moviesApi.services;

import dev.dipeshshrestha.moviesApi.documents.Movie;
import dev.dipeshshrestha.moviesApi.documents.Review;
import dev.dipeshshrestha.moviesApi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(Review review, String movieImdbId) {
        Review newReview = reviewRepository.insert(review);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(movieImdbId))
                .apply(new Update().push("reviewIds",review))
                .first();
        return newReview;
    }
}
