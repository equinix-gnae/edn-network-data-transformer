package com.equinix.edn.networkdatatransformer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "source cannot be null or empty")
    private String source;
    @NotEmpty(message = "subscription name cannot be null or empty")
    @JsonProperty("subscription-name")
    private String subscriptionName;
    @JsonProperty("event-id")
    private String eventId;
    private String ibx;
    private String metro;
    private String region;
    private long timestamp;
    private String time;
    private String prefix;
    @NotNull(message = "Updates cannot be null")
    private List<GnmiDataPoint> updates;
}
