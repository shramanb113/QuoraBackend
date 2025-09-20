package com.example.QuoraReactiveApp.consumers;

import com.example.QuoraReactiveApp.config.KafkaConfig;
import com.example.QuoraReactiveApp.events.ViewCountEvent;
import com.example.QuoraReactiveApp.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class KafkaEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(
            topics= KafkaConfig.TOPIC_NAME,
            groupId = "view-count-consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleViewCountEvent(ViewCountEvent viewCountEvent){
        questionRepository.findById(new ObjectId(viewCountEvent.getTargetId()))
                .flatMap(question -> {
                    question.setViews(question.getViews()+1);
                    return questionRepository.save(question);
                })
                .subscribe(updatedQuestion-> {
                    System.out.println("View Count incremented for the question " + updatedQuestion.getId());
                },error->{
                    System.out.println("Error incrementing message "+error.getMessage());
                });
    }

}
