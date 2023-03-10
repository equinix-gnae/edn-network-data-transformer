package com.equinix.edn.networkdatatransformer.controller;

import javax.validation.Valid;

import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.service.GnmiDataProcessor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

@Validated
@Controller
@Slf4j
public class GnmiKafkaListener {

    private final GnmiDataProcessor gnmiDataProcessor;

    public GnmiKafkaListener(GnmiDataProcessor gnmiDataProcessor) {
        this.gnmiDataProcessor = gnmiDataProcessor;
    }

    //Consume raw network data from Kafka
    @KafkaListener(topics = "${edn.kafka.topic.gnmi-raw-data.topic-name}",
            containerFactory = "networDataTransformerListenerContainerFactory",
            errorHandler = "gnmiKafkaListenerExceptionHandler")
    public void rawNetworkDataListener(@Payload @Valid GnmiMessage message, Acknowledgment acknowledgment) {
        log.info("[GNMI] Raw Network Data Payload {}", message);
        gnmiDataProcessor.process(message);
        acknowledgment.acknowledge();
    }
}
