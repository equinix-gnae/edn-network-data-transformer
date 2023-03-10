package com.equinix.edn.networkdatatransformer.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiMessage {
    @NotEmpty(message = "Name is required")
    private String name;
    private long timestamp;
    @NotNull(message = "Tags are required")
    private GnmiTag tags;
    private GnmiSensor values;
}
