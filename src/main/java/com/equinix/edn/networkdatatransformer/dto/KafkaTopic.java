package com.equinix.edn.networkdatatransformer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaTopic {
    private String topicName;
    private Integer partitions;
    private Short replicas;


}
