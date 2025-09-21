package com.example.QuoraReactiveApp.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDTO {

    @NotBlank(message = "Question ID is required")
    private String questionId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Answer content cannot be empty")
    @Size(min = 10, message = "Answer must be at least 10 characters long")
    private String content;
}
