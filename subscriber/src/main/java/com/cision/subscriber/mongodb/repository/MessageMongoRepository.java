package com.cision.subscriber.mongodb.repository;

import com.cision.subscriber.model.MessageDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessageMongoRepository extends ReactiveMongoRepository<MessageDocument, String> {
}
