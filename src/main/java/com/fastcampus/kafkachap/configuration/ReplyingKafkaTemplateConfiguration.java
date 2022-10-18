package com.fastcampus.kafkachap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class ReplyingKafkaTemplateConfiguration {

//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(ProducerFactory<String, String > producerFactory,
//        ConcurrentMessageListenerContainer replyingContainer) {
//        return new ReplyingKafkaTemplate<>(producerFactory, replyingContainer);
//    }
//
//    @Bean
//    public ConcurrentMessageListenerContainer replyingContainer(ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
//        ConcurrentMessageListenerContainer<String, String> container = containerFactory.createContainer("clip3-replies");
//        container.getContainerProperties().setGroupId("clip3-replies-container-id");
//        return container;
//    }
}
