package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.AnswerRequestDTO;
import com.example.QuoraReactiveApp.models.Answer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface IAnswerService {

    Mono<Answer> createAnswer(AnswerRequestDTO answerRequestDTO);

    Mono<Answer> getAnswerById(String Id);
}
