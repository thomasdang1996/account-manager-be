package com.dang.accountmanager.be.handler;

import avrogenerated.accountmanager.AccountCreated;
import avrogenerated.accountmanager.AccountCreationFailed;
import avrogenerated.accountmanager.CreateAccountPayload;
import com.dang.commonlib.messaging.MessageBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Class responsible for handling messages
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHandler {
    private final MessageBus messageBus;

    @KafkaListener(
            topics = "${app.kafka.consumer.topic}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "my-group-id"
    )
    public void receive(CreateAccountPayload message) {
        log.info("Data received: {}", message);
        messageBus.sendMessage(
                getRecord(message)
        );
    }

    private SpecificRecord getRecord(CreateAccountPayload message) {
        if (accountExists(message)) {
            return new AccountCreationFailed(
                    "Username " + message.getUsername() + " is already taken"
            );
        }

        return new AccountCreated(
                UUID.randomUUID(),
                message.getUsername()
        );
    }

    private static boolean accountExists(CreateAccountPayload message) {
        return message.getUsername().equals("someExistingGuy55");
    }
}
