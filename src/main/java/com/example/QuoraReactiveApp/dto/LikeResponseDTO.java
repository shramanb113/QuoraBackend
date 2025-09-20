package com.example.QuoraReactiveApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDTO {

    private String targetId;
    private String targetType;
    private boolean islIke;
    private LocalDateTime createdAt;


}
