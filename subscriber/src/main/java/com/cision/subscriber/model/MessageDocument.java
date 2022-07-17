package com.cision.subscriber.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Document("message_document")
@Data
public class MessageDocument implements Serializable {

    private String content;
    private LocalDateTime timestamp;
    private int longestPalindromeSize;

}
