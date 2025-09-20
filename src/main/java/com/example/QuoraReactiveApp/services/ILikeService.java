package com.example.QuoraReactiveApp.services;


import com.example.QuoraReactiveApp.dto.LikeRequestDTO;
import com.example.QuoraReactiveApp.dto.LikeResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ILikeService {

    Mono<LikeResponseDTO> createLike(LikeRequestDTO likeRequestDTO);
    Mono<LikeResponseDTO> countLikeByTargetIdAndTargetType (String targetId , String targetType);
    Mono<LikeResponseDTO> countDislikeTargetIdAndTargetType(String targetId , String targetType);
    Mono<LikeResponseDTO> toggleLike(String targetId , String targetType);
}
