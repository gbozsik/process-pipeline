package com.cision.subscriber.redis;

import com.cision.subscriber.service.MessageService;
import org.slf4j.Logger;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

public class RedisMessageSubscriber implements MessageListener {

    private final Logger logger = getLogger(RedisMessageSubscriber.class);

    private final MessageService messageService;

    public RedisMessageSubscriber(MessageService messageService) {
        this.messageService = messageService;
    }

    public void onMessage(final Message message, final byte[] pattern) {
        Mono.just(message)
                .doOnNext(m -> {
                    logger.info("Message received: {}", new String(message.getBody()));
                    messageService.handleReceivedMessage(message);
                })
                .doOnSuccess(it -> logger.info("Message stored in database: {}", new String(message.getBody())))
                .doOnError(throwable -> logger.error("Failed to store message in dtabase", throwable))
                .subscribe();

    }
}

