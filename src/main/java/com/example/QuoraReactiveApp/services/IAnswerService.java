package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.AnswerRequestDTO;
import com.example.QuoraReactiveApp.models.Answer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IAnswerService {

    Mono<Answer> createAnswer(AnswerRequestDTO answerRequestDTO);
    Mono<Answer> getAnswerById(String id);
    Flux<Answer> getAllAnswers();
    Flux<Answer> getAnswersByQuestionId(String questionId);
    Flux<Answer> getAnswersByUserId(String userId);
    Mono<Answer> updateAnswer(String id, AnswerRequestDTO answerRequestDTO);
    Mono<Void> deleteAnswer(String id);
    Mono<Long> countAnswersByQuestionId(String questionId);
    Mono<Boolean> existsById(String id);
}
