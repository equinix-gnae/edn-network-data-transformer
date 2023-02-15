package com.equinix.edn.networkdatatransformer.controller;

import javax.validation.Valid;

import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.service.GnmiDataProcessor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import lombok.extern.slf4j.Slf4j;

@Validated
@Controller
@Slf4j
public class GnmiKafkaController {

    private final GnmiDataProcessor gnmiDataProcessor;

    public GnmiKafkaController(GnmiDataProcessor gnmiDataProcessor) {
        this.gnmiDataProcessor = gnmiDataProcessor;
    }

    @KafkaListener(topics = "${edn.kafka.topic.gnmi-raw-data.topic-name}", containerFactory = "networkDataTransformerConsumerFactory")
    public void rawNetworkDataListener(@Payload @Valid  GnmiMessage message, Acknowledgment acknowledgment) {
        log.info("[GNMI] Raw Network Data Payload {}", message);
        gnmiDataProcessor.process(message);
        acknowledgment.acknowledge();
    }
}
