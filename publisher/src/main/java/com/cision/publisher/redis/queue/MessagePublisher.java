package com.cision.publisher.redis.queue;

import com.cision.publisher.model.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

public interface MessagePublisher {

    void publish(final MessageModel messageModel) throws JsonProcessingException;
}
