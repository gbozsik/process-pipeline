package com.cision.publisher.controller;

import com.cision.publisher.model.MessageModel;
import com.cision.publisher.model.RestResponse;
import com.cision.publisher.service.SubscribeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {
    @Autowired
    private SubscribeServiceImpl subscribeServiceImpl;

    @PostMapping("/store-message")
    public Mono<RestResponse> storeMessage(@RequestBody MessageModel messageModel) {
        return Mono.just(messageModel)
                .flatMap(m -> subscribeServiceImpl.storeMessage(m)).log();
    }
}
