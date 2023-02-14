package com.equinix.edn.networkdatatransformer.config.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaTopicConfig {
    private String topicName;
    private Integer partitions;
    private Integer replicas;
}
