package com.example.QuoraReactiveApp.controllers;

import com.example.QuoraReactiveApp.dto.AnswerRequestDTO;
import com.example.QuoraReactiveApp.dto.AnswerResponseDTO;
import com.example.QuoraReactiveApp.mapper.AnswerMapper;
import com.example.QuoraReactiveApp.models.Answer;
import com.example.QuoraReactiveApp.services.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;
    private final AnswerMapper answerMapper;

    @PostMapping
    public Mono<ResponseEntity<AnswerResponseDTO>> createAnswer(@RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.createAnswer(answerRequestDTO)
                .map(answerMapper::toResponseDTO)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(id)
                .map(answerMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<AnswerResponseDTO> getAllAnswers() {
        return answerService.getAllAnswers()
                .map(answerMapper::toResponseDTO);
    }

    @GetMapping("/question/{questionId}")
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.getAnswersByQuestionId(questionId)
                .map(answerMapper::toResponseDTO);
    }

    @GetMapping("/user/{userId}")
    public Flux<AnswerResponseDTO> getAnswersByUserId(@PathVariable String userId) {
        return answerService.getAnswersByUserId(userId)
                .map(answerMapper::toResponseDTO);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AnswerResponseDTO>> updateAnswer(
            @PathVariable String id,
            @RequestBody AnswerRequestDTO answerRequestDTO) {
        return answerService.updateAnswer(id, answerRequestDTO)
                .map(answerMapper::toResponseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnswer(@PathVariable String id) {
        return answerService.deleteAnswer(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(e -> Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/count/question/{questionId}")
    public Mono<ResponseEntity<Long>> countAnswersByQuestionId(@PathVariable String questionId) {
        return answerService.countAnswersByQuestionId(questionId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{id}")
    public Mono<ResponseEntity<Boolean>> existsById(@PathVariable String id) {
        return answerService.existsById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}