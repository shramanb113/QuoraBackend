package com.example.QuoraReactiveApp.controllers;

import com.example.QuoraReactiveApp.dto.ErrorResponse;
import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.createQuestion(questionRequestDTO)
                .doOnError(error-> System.out.println("Error occurred "+error));
    }


    @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestion(@PathVariable String id) {
        return questionService.getQuestionById(id)
                .doOnError(error -> System.out.println("Error is this :"+error));
    }

    @GetMapping()
    public Flux<QuestionResponseDTO> getQuestion(
            @RequestParam(required = false) String cursorTimeStamp,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        return questionService.getAllQuestion(cursorTimeStamp,pageSize)
                .doOnError(error -> System.out.println("Error is this :"+error));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> getPaginatedQuestion(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return questionService.getPaginatedQuestion(query, pageNumber, pageSize);
    }





}
