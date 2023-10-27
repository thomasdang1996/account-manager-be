package com.dang.accountmanager.accountmanagerbe.handler;

import avrogenerated.accountmanager.CreateAccountPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Class responsible for handling messages
 */
@Slf4j
@Component
public class NotificationHandler {
    @KafkaListener(
            topics = "${app.kafka.consumer.topic}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "my-group-id"
    )
    public void receive(CreateAccountPayload message) {
        log.info("Data received: {}", message);
    }
}
