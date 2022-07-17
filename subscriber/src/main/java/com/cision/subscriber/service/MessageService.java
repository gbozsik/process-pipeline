package com.cision.subscriber.service;

import com.cision.subscriber.model.MessageDocument;
import org.springframework.data.redis.connection.Message;
import reactor.core.publisher.Flux;

public interface MessageService {

    void handleReceivedMessage(Message message);

    Flux<MessageDocument> getAllMessages();
}
