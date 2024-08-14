package dev.dipeshshrestha.moviesApi.repositories;

import dev.dipeshshrestha.moviesApi.documents.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
