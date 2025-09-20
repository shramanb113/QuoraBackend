package com.example.QuoraReactiveApp.mapper;

import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.models.Question;

public class QuestionMapper {

    public static QuestionResponseDTO toQuestionResponseDTO(Question question){
        return QuestionResponseDTO.builder()
                .id(question.getId().toString())
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
