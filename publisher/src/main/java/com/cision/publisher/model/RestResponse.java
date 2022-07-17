package com.cision.publisher.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestResponse {

    private String errorMessage;
    private MessageModel messageModel;
    private LocalDateTime timeStamp;
}
