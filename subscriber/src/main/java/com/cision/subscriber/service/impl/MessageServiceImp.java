package com.cision.subscriber.service.impl;

import com.cision.subscriber.model.MessageDocument;
import com.cision.subscriber.mongodb.repository.MessageMongoRepository;
import com.cision.subscriber.service.MessageService;
import com.cision.subscriber.service.PalindromeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public class MessageServiceImp implements MessageService {

    private Logger logger = getLogger(MessageServiceImp.class);

    private final MessageMongoRepository messageMongoRepository;

    private final ObjectMapper objectMapper;

    private final PalindromeService palindromeService;

    public MessageServiceImp(MessageMongoRepository messageMongoRepository,
                             ObjectMapper objectMapper,
                             PalindromeService palindromeService1) {
        this.messageMongoRepository = messageMongoRepository;
        this.objectMapper = objectMapper;
        this.palindromeService = palindromeService1;
    }

    public void handleReceivedMessage(Message message) {
        MessageDocument messageDocument = null;
        try {
            messageDocument = objectMapper.readValue(message.getBody(), MessageDocument.class);
        } catch (IOException e) {
            logger.error("Failed to parse received message!", e);
        }
        if (messageDocument != null && messageDocument.getContent() != null) {
            messageDocument.setTimestamp(LocalDateTime.now());
            messageDocument.setLongestPalindromeSize(palindromeService.getLongestPalindromeSize(messageDocument.getContent()));
            messageMongoRepository.insert(messageDocument)
                    .doOnSuccess(it -> logger.debug("Message inserted to Database {}", it))
                    .doOnError(throwable -> logger.error("Failed to insert message to Database", throwable))
                    .subscribe();
        }
    }
}
