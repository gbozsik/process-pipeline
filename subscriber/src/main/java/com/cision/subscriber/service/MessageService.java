package com.cision.subscriber.service;

import org.springframework.data.redis.connection.Message;

public interface MessageService {

    void handleReceivedMessage(Message message);
}
