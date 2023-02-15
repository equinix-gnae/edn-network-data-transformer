package com.equinix.edn.networkdatatransformer.config.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaTopicConfig {
    private String topicName;
    private Integer partitions;
    private Short replicas;


}
