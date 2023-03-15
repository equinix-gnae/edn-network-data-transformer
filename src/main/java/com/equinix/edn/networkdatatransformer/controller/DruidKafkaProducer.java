package com.equinix.edn.networkdatatransformer.controller;

import java.util.List;

import com.equinix.edn.networkdatatransformer.dto.DruidMessage;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DruidKafkaProducer {

    private final KafkaTemplate<String, DruidMessage> kafkaTemplate;

    public DruidKafkaProducer(KafkaTemplate<String, DruidMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessages(List<DruidMessage> messages, String topic) {
        if (messages == null || messages.isEmpty()) {
            return;
        }
        messages.forEach(message -> {
           sendDruidMessageToKafka(message, topic);
        });
    }

    private void sendDruidMessageToKafka(DruidMessage message, String topic) {
        Message<DruidMessage> kafkaMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.MESSAGE_KEY, message.getDataPointName())
                .build();

        ListenableFuture<SendResult<String, DruidMessage>> future = kafkaTemplate.send(kafkaMessage);
        future.addCallback(new ListenableFutureCallback<SendResult<String, DruidMessage>>() {
            @Override
            public void onSuccess(SendResult<String, DruidMessage> result) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }
        });
    }


}
