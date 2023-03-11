package com.equinix.edn.networkdatatransformer.dto;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Druid Message POJO with datapoint_name, event_id, event_time_iso, event_timestamp, event_processed_time_iso,
//event_processed_time, tags, metric_value, state
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DruidMessage {
    @JsonProperty("datapoint_name")
    private String dataPointName;
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("event_time_iso")
    private String eventTimeIso;
    @JsonProperty("event_timestamp")
    private Long eventTimestamp;
    @JsonProperty("event_processed_time_iso")
    private String eventProcessedTimeIso;
    @JsonProperty("event_processed_time")
    private Long eventProcessedTime;
    private LinkedHashMap<String, Object> tags;
    @JsonProperty("metric_value")
    private Long metricValue;
    private String state;
}
