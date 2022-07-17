package com.cision.subscriber.service.configuration;

import com.cision.subscriber.mongodb.repository.MessageMongoRepository;
import com.cision.subscriber.redis.RedisMessageSubscriber;
import com.cision.subscriber.service.MessageService;
import com.cision.subscriber.service.impl.MessageServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;

public class ServiceConfiguration {

//    @Bean
//    public MessageListener messageListener(MessageMongoRepository messageMongoRepository, ObjectMapper objectMapper) {
//        return new RedisMessageSubscriber();
//    }
//
//    @Bean
//    public MessageService messageService(MessageMongoRepository messageMongoRepository, ObjectMapper objectMapper) {
//        return new MessageServiceImp(messageMongoRepository, objectMapper);
//    }

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
//                .serializers(LOCAL_DATETIME_SERIALIZER);
//    }
//
//    @Bean
//    @Primary
//    public ObjectMapper objectMapper() {
//        JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
//        return new ObjectMapper()
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
//                .registerModule(module);
//    }
}
