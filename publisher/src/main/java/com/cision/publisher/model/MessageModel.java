package com.cision.publisher.model;

import lombok.*;

import java.time.LocalDateTime;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MessageModel {

    private String content;
    private LocalDateTime timestamp;
}
