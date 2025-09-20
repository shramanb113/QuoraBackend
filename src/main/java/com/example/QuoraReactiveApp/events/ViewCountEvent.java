package com.example.QuoraReactiveApp.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ViewCountEvent {
    private String targetId;
    private String targetType;
    private LocalDateTime timeStamp;
}
