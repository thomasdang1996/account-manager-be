package com.dang.accountmanager.be.handler;

import avrogenerated.accountmanager.AccountCreated;
import avrogenerated.accountmanager.AccountCreationFailed;
import avrogenerated.accountmanager.CreateAccountPayload;
import com.dang.accountmanager.be.mapper.AccountMapper;
import com.dang.accountmanager.be.repository.AccountRepository;
import com.dang.commonlib.messaging.MessageBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Class responsible for handling messages
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationHandler {
    private final MessageBus messageBus;
    private final AccountRepository repository;
    private final AccountMapper mapper;

    @KafkaListener(
            topics = "${app.kafka.consumer.topic}",
            groupId = "my-group-id"
    )
    public void receive(ConsumerRecord<String, SpecificRecord> message) {
        log.info("Received {}: {}", message.value().getClass().getCanonicalName(), message.value());
        SpecificRecord response = validateMessage((CreateAccountPayload) message.value());
        messageBus.sendMessage(
                response,
                List.of(new RecordHeader("messageId",message.headers().toArray()[0].value()))
        );
    }

    private SpecificRecord validateMessage(CreateAccountPayload message) {
        if (accountExists(message)) {
            return new AccountCreationFailed(
                    "Username " + message.getUsername() + " is already taken"
            );
        }
        UUID accountId = UUID.randomUUID();
        repository.save(
                mapper.toAccountEntity(
                        accountId,
                        message.getUsername(),
                        message.getUsername()
                )
        );
        return new AccountCreated(
                accountId,
                message.getUsername()
        );
    }

    private boolean accountExists(CreateAccountPayload message) {
        return repository.existsByUsername(message.getUsername());
    }
}
