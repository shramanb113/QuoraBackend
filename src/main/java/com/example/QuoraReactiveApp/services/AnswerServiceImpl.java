package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.AnswerRequestDTO;
import com.example.QuoraReactiveApp.mapper.AnswerMapper;
import com.example.QuoraReactiveApp.models.Answer;
import com.example.QuoraReactiveApp.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements IAnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public Mono<Answer> createAnswer(AnswerRequestDTO answerRequestDTO) {
        Answer answer = answerMapper.toEntity(answerRequestDTO);
        return answerRepository.save(answer);
    }

    @Override
    public Mono<Answer> getAnswerById(String id) {
        return answerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found with id: " + id)));
    }

    @Override
    public Flux<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Flux<Answer> getAnswersByQuestionId(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public Flux<Answer> getAnswersByUserId(String userId) {
        return answerRepository.findByUserId(userId);
    }

    @Override
    public Mono<Answer> updateAnswer(String id, AnswerRequestDTO answerRequestDTO) {
        return answerRepository.findById(id)
                .flatMap(existingAnswer -> {
                    answerMapper.updateEntityFromDTO(answerRequestDTO, existingAnswer);
                    return answerRepository.save(existingAnswer);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found with id: " + id)));
    }

    @Override
    public Mono<Void> deleteAnswer(String id) {
        return answerRepository.findById(id)
                .flatMap(answerRepository::delete)
                .switchIfEmpty(Mono.error(new RuntimeException("Answer not found with id: " + id)));
    }

    @Override
    public Mono<Long> countAnswersByQuestionId(String questionId) {
        return answerRepository.countByQuestionId(questionId);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return answerRepository.existsById(id);
    }
}