package com.cision.subscriber.controler;

import com.cision.subscriber.model.MessageDocument;
import com.cision.subscriber.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class Controller {

    private final MessageService messageService;

    public Controller(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get-all-message")
    public Flux<MessageDocument> getAllMessage() {
        return messageService.getAllMessages();
    }
}
