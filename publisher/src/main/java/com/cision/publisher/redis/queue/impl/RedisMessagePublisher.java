package com.cision.publisher.redis.queue.impl;

import com.cision.publisher.model.MessageModel;
import com.cision.publisher.redis.queue.MessagePublisher;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class RedisMessagePublisher implements MessagePublisher {

    private final Logger logger = getLogger(RedisMessagePublisher.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final MessageModel messageModel) {
        Mono.just(messageModel)
                .doOnNext(mesage ->
                    redisTemplate.convertAndSend(topic.getTopic(), mesage))
                .subscribe();

    }
}
