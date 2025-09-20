package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.events.ViewCountEvent;
import com.example.QuoraReactiveApp.mapper.QuestionMapper;
import com.example.QuoraReactiveApp.models.Question;
import com.example.QuoraReactiveApp.producers.KafkaEventProducer;
import com.example.QuoraReactiveApp.repositories.QuestionRepository;
import com.example.QuoraReactiveApp.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;


    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionMapper::toQuestionResponseDTO)
                .doOnSuccess(response->System.out.println("Question Created Successfully"+response))
                .doOnError(error-> System.out.println("Error in making " +error));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        try {
            ObjectId objectId = new ObjectId(id); // Validates ID format
            return questionRepository.findById(objectId)
                    .map(QuestionMapper::toQuestionResponseDTO)
                    .doOnSuccess(response->{
                        System.out.println("Response fetched successfully " + response);
                        ViewCountEvent viewCountEvent = new ViewCountEvent(id,"question",LocalDateTime.now());
                        kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                    })
                    .switchIfEmpty(Mono.error(new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Question not found with id: " + id
                    )));
        } catch (IllegalArgumentException e) {
            return Mono.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid question ID format"
            ));
        }
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestion(String cursorTimeStamp,int pageSize) {

        if(CursorUtils.IsValidCursorTimeStamp(cursorTimeStamp)){
            return questionRepository.findByCreatedAtGreaterThanEqualOrderByCreatedAtDesc(LocalDateTime.parse(cursorTimeStamp),PageRequest.of(0, pageSize))
                    .map(QuestionMapper::toQuestionResponseDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Question not found "
                    )));
        }
        else{
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(pageSize)
                    .map(QuestionMapper::toQuestionResponseDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Question not found "
                    )));

        }


    }

    @Override
    public Flux<QuestionResponseDTO> getPaginatedQuestion(String searchItem,int pageNumber,int pageSize) {
        return questionRepository.findTitleOrContentContainingIgnoreCase(searchItem, PageRequest.of(pageNumber,pageSize))
                .map(QuestionMapper::toQuestionResponseDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Question not found "
                )));
    }


}
