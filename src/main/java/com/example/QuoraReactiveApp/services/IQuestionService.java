package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.models.Question;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;

@Service
public interface IQuestionService {
    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    Mono<QuestionResponseDTO> getQuestionById(String id);
    Flux <QuestionResponseDTO> getAllQuestion(String cursorTimeStamp,int pageSize);
    Flux <QuestionResponseDTO> getPaginatedQuestion(String searchItem,int pageNumber,int pageSize);

}
