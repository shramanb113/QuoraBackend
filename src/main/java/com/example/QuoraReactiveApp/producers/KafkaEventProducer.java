package com.example.QuoraReactiveApp.producers;

import com.example.QuoraReactiveApp.config.KafkaConfig;
import com.example.QuoraReactiveApp.events.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.cert.CertPathBuilderResult;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent countEvent){
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME,countEvent.getTargetId(),countEvent)
                .whenComplete((result,err)->{
                    if(err!=null){
                        System.out.println("Error publishing viewcount event: "+err.getMessage());
                    }
                });
    }
}
