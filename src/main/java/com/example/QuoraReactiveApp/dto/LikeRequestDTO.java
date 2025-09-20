package com.example.QuoraReactiveApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequestDTO {

    @NotBlank(message = "Id shouldn't be blank")
    private String targetId;

    @NotBlank(message = "type shouldn't be blank")
    private String targetType;

    @NotNull(message = "Ener Like or Dislike in terms of True or False")
    private boolean isLike;


}
