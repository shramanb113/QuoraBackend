package com.example.QuoraReactiveApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "likes")
public class Likes {

    @Id
    private String id;

    private String targetType;//QUESTION , ANSWER ,COMMENTS

    private String targetId;

    private boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
