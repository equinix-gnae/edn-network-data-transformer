package com.equinix.edn.networkdatatransformer.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.equinix.edn.networkdatatransformer.config.kafka.EdnKafkaConfig;
import com.equinix.edn.networkdatatransformer.constants.KafkaConfigConstants;
import com.equinix.edn.networkdatatransformer.controller.DruidKafkaProducer;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.transformer.druid.DruidDataSourceType;
import com.equinix.edn.networkdatatransformer.transformer.druid.DruidTransformerMapBuilder;
import com.equinix.edn.networkdatatransformer.util.DtoUtils;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GnmiDataProcessor {

    private final DruidTransformerMapBuilder druidTransformerMapBuilder;
    private final EdnKafkaConfig ednKafkaConfig;
    private final DruidKafkaProducer druidKafkaProducer;

    public GnmiDataProcessor(DruidTransformerMapBuilder druidTransformerMapBuilder, EdnKafkaConfig ednKafkaConfig,
                             DruidKafkaProducer druidKafkaProducer) {
        this.druidTransformerMapBuilder = druidTransformerMapBuilder;
        this.ednKafkaConfig = ednKafkaConfig;
        this.druidKafkaProducer = druidKafkaProducer;
    }


    public void process(List<LinkedHashMap<String, Object>> jsonMessage) {
        log.info("[GNMI] Processing GNMI event {}", jsonMessage);
        GnmiMessage gnmiMessage = DtoUtils.convertLinkedHashMapToGnmiMessage(jsonMessage.get(0));
        log.info("[GNMI] GNMI Message {}", gnmiMessage);

        List<DruidMessage> druidMessages =
                (List<DruidMessage>) druidTransformerMapBuilder
                        .getTransformer(DruidDataSourceType.GNMI)
                        .transform(gnmiMessage);

        log.info("[GNMI] Druid Messages {}", druidMessages);
        sendDruidMessagesToKafka(druidMessages);
    }

    private void sendDruidMessagesToKafka(List<DruidMessage> druidMessages) {
        druidKafkaProducer.sendMessages(druidMessages,
                ednKafkaConfig.getTopic().get(KafkaConfigConstants.TOPIC_GROUP_NORMALIZED_DATA).getTopicName());
    }
}
