package com.equinix.edn.networkdatatransformer.config.kafka;

import javax.annotation.PostConstruct;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("edn.kafka")
@Slf4j
public class KafkaConfig {

    private String brokerAddress;
    private Map<String, KafkaTopicConfig> topic;

    @PostConstruct
    public void logConfiguration() {
        log.info("[Kafka Configuration] Broker Address:{}, topic:{}", brokerAddress, topic);
    }
}
