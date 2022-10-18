package com.fastcampus.kafkachap.produer;

import com.fastcampus.kafkachap.model.Animal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ClipProducer {

    public static final int DFUALT_TIMEOUT = 10;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Animal> kafkaJsonTemplate;
//    private final RoutingKafkaTemplate routingKafkaTemplate;
//    private final ReplyingKafkaTemplate replyingKafkaTemplate;

    public ClipProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Animal> kafkaJsonTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    public void async(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new KafkaSendCallback<>(){

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Success to send me");
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ProducerRecord<Object, Object> record = ex.getFailedProducerRecord();
                System.out.println("Fail to send message. record=" + record);
            }
        });
    }

    public void sync(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        try {
            future.get(10, TimeUnit.SECONDS);
            System.out.println("Success sync to send me");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void asyncAnimal(String topic, Animal animal) {
        kafkaJsonTemplate.send(topic, animal);
    }

//    public void routingSend(String topic, String message) {
//        routingKafkaTemplate.send(topic, message);
//    }
//
//    public void routingSendBytes(String topic, byte[] message) {
//        routingKafkaTemplate.send(topic, message);
//    }
//
//    public void replyingSend(String topic, String message) throws ExecutionException, InterruptedException, TimeoutException {
//        ProducerRecord<String, String> record = new ProducerRecord<>(topic,message);
//        RequestReplyFuture<String, String , String> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
//
//        ConsumerRecord<String, String> consumerRecord = replyFuture.get(DFUALT_TIMEOUT, TimeUnit.SECONDS);
//        System.out.println(consumerRecord.value());
//    }


}
