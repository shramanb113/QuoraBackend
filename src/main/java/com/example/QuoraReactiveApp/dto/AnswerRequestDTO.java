package com.example.QuoraReactiveApp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequestDTO {

    @NotBlank(message = "Content is required")
    @Size(min = 10 , max = 1000 , message = "size should be 10 to 1000 characters")
    private String content;

    private String questionId;

}
