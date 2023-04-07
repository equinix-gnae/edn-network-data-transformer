package com.equinix.edn.networkdatatransformer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiTag {
    private String ibx;
    private String metro;
    private String region;
    private String source;

    @JsonAlias({"port_port-id"})
    @JsonProperty(value = "interface_name")
    private String interfaceName;

    @JsonProperty(value = "subscription_name")
    private String subscriptionName;

    @JsonAlias({"neighbor_neighbor-address", "neighbor_ip-address"})
    private String neighborAddress;

    @JsonAlias({"network-instance_name", "vprn_service-name"})
    private String networkInstanceName;
}
