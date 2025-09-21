package com.example.QuoraReactiveApp.mapper;

import com.example.QuoraReactiveApp.dto.AnswerRequestDTO;
import com.example.QuoraReactiveApp.dto.AnswerResponseDTO;
import com.example.QuoraReactiveApp.models.Answer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnswerMapper {

    public Answer toEntity(AnswerRequestDTO dto) {
        return Answer.builder()
                .questionId(dto.getQuestionId())
                .userId(dto.getUserId())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .upvotes(0)
                .downvotes(0)
                .build();
    }

    public AnswerResponseDTO toResponseDTO(Answer answer) {
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .questionId(answer.getQuestionId())
                .userId(answer.getUserId())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .updatedAt(answer.getUpdatedAt())
                .upvotes(answer.getUpvotes())
                .downvotes(answer.getDownvotes())
                .build();
    }

    public void updateEntityFromDTO(AnswerRequestDTO dto, Answer answer) {
        answer.setContent(dto.getContent());
        answer.setUpdatedAt(LocalDateTime.now());
    }
}