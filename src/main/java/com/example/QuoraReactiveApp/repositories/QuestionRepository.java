package com.example.QuoraReactiveApp.repositories;

import com.example.QuoraReactiveApp.models.Question;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, ObjectId> {

    @Query("{ '$or': [ { 'title': { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } } ] }")
    Flux<Question> findTitleOrContentContainingIgnoreCase(String searchItem,Pageable pageable);

    Flux<Question> findByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDateTime cursorTimeStamp , Pageable pageable);

    Flux<Question> findTop10ByOrderByCreatedAtAsc();


}
