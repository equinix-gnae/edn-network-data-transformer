package com.equinix.edn.networkdatatransformer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DruidMessage {
    @JsonProperty("data_point_name")
    private DataPointName dataPointName;
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("event_time_iso")
    private String eventTimeIso;
    @JsonProperty("event_timestamp")
    private Long eventTimestamp;
    @JsonProperty("event_processed_time_iso")
    private String eventProcessedTimeIso;
    private String router;
    private String ibx;
    private String metro;
    private String region;
    @JsonProperty("interface_name")
    private String interfaceName;
    @JsonProperty("network_instance")
    private String networkInstance;
    private String value;

}
