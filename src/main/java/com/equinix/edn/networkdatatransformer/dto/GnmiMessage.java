package com.equinix.edn.networkdatatransformer.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiMessage {
    private String source;
    private String subscriptionName;
    private long timestamp;
    private Instant time;
    private String prefix;
    private List<GnmiDataPoint> updates;
}
