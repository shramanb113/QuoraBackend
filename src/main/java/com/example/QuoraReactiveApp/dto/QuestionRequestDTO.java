package com.example.QuoraReactiveApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 10 , max = 100 , message = "size should be 10 to 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10 , max = 1000 , message = "size should be 10 to 1000 characters")
    private String content;
}
