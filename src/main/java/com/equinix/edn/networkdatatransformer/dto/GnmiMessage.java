package com.equinix.edn.networkdatatransformer.dto;

import javax.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiMessage {
    @NotEmpty
    private String source;
    @NotEmpty
    @JsonProperty("subscription-name")
    private String subscriptionName;
    private String ibx;
    private String metro;
    private String region;
    private long timestamp;
    private String time;
    private String prefix;
    private List<GnmiDataPoint> updates;
}
