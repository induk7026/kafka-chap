package com.fastcampus.kafkachap;

import com.fastcampus.kafkachap.model.Animal;
import com.fastcampus.kafkachap.produer.ClipProducer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@SpringBootApplication
public class KafkaChapApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaChapApplication.class, args);
    }


    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer, KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            clipProducer.async("clip4-listener", "Hello, Clip4 container.");
            clipProducer.async("clip4-listener", "Hello, Clip4 pause.");
            clipProducer.asyncAnimal("clip4-animal", new Animal("puppy", 1));
        };
//        return args -> {
//            clipProducer.async("clip3", "Hello, Clip3-async");
//            clipProducer.sync("clip3", "Hello, Clip3-sync");
//            clipProducer.routingSend("clip3", "Hello, Clip3-routing");
//            clipProducer.replyingSend("clip3-request", "Hello, Clip3-replying");
//            clipProducer.routingSendBytes("clip3-bytes", "Hello, Clip3-routing-bytes".getBytes(StandardCharsets.UTF_8));
//        };
    }
}
