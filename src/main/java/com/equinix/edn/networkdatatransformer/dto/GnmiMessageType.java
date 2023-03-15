package com.equinix.edn.networkdatatransformer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum GnmiMessageType {
    @JsonAlias({"junos-interface", "nokia-interface", "arrcus-interface"})
    INTERFACE_DATA,
    @JsonAlias({"junos-network-instance", "nokia-network-instance", "arrcus-network-instance"})
    NETWORK_INSTANCE_DATA
}
