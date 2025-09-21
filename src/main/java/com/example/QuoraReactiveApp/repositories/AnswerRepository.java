package com.example.QuoraReactiveApp.repositories;

import com.example.QuoraReactiveApp.models.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer,String>{

    Flux<Answer> findByQuestionId(String questionId);
    Flux<Answer> findByUserId(String userId);
    Mono<Long> countByQuestionId(String questionId);


}
