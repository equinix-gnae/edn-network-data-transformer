package com.equinix.edn.networkdatatransformer.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiDataPoint {
    @JsonProperty("Path")
    private String path;
    private Map<String, String> values;
}
