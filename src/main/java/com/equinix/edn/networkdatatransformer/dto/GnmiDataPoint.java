package com.equinix.edn.networkdatatransformer.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiDataPoint {
    private String path;
    private Map<String, String> values;
}
