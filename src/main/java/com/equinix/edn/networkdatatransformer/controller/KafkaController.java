package com.equinix.edn.networkdatatransformer.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class KafkaController {

    @KafkaListener(topics = "${edn.kafka.topic.raw-data.topic-name}", containerFactory = "networkDataTransformerConsumerFactory")
    public void rawNetworkDataListener(Message<String> message, Acknowledgment acknowledgment) {

        log.info("Raw Network Data Payload {}", message.getPayload());
        acknowledgment.acknowledge();
    }
}
