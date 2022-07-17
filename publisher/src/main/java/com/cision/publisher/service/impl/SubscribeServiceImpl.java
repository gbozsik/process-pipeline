package com.cision.publisher.service.impl;

import com.cision.publisher.model.MessageModel;
import com.cision.publisher.model.RestResponse;
import com.cision.publisher.redis.queue.impl.RedisMessagePublisher;
import com.cision.publisher.service.SubscriberService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public class SubscribeServiceImpl implements SubscriberService {

    private final Logger logger = getLogger(SubscribeServiceImpl.class);

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    public Mono<RestResponse> storeMessage(MessageModel messageModel) {
        return Mono.just(messageModel)
                .map(message -> {
                    RestResponse response = new RestResponse();
                    var errorMessage = validationError(messageModel);
                    response.setMessageModel(messageModel);
                    response.setTimeStamp(LocalDateTime.now());
                    if (errorMessage != null) {
                        response.setErrorMessage(errorMessage);
                        return response;
                    }
                    redisMessagePublisher.publish(message);
                    return response;
//                    throw new RuntimeException("test");
                })
                .doOnError(throwable -> {
                    logger.error("Failed to pulish message to Redis", throwable);
                })
                .doOnSuccess(o -> {
                    logger.info("Message has been published");
                });
    }

    protected String validationError(MessageModel messageModel) {
        if (messageModel.getContent().isEmpty() || messageModel.getContent().isBlank()) {
            return "Message ontent can not be empty";
        }
        return null;
    }
}
