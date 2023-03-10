package com.equinix.edn.networkdatatransformer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnmiSensor {
    @JsonProperty("carrier-transitions")
    private int carrierTransitions;
    @JsonProperty("high-speed")
    private int highSpeed;
    @JsonProperty("in-broadcast-pkts")
    @JsonAlias("in-broadcast-packets")
    private int inBroadcastPkts;
    @JsonProperty("in-octets")
    private int inOctets;
    @JsonProperty("in-pkts")
    @JsonAlias("in-packets")
    private int inPkts;
    @JsonProperty("in-unicast-pkts")
    @JsonAlias("in-unicast-packets")
    private int inUnicastPkts;
    @JsonProperty("init_time")
    private int initTime;
    @JsonProperty("out-broadcast-pkts")
    @JsonAlias("out-broadcast-packets")
    private int outBroadcastPkts;
    @JsonAlias({"out-multicast-packets", "out-multicast-pkts"})
    private int outMulticastPkts;
    @JsonProperty("out-octets")
    private long outOctets;
    @JsonProperty("out-pkts")
    @JsonAlias("out-packets")
    private long outPkts;
    @JsonProperty("out-unicast-pkts")
    @JsonAlias("out-unicast-packets")
    private int outUnicastPkts;
    @JsonProperty("parent_ae_name")
    private String parentAeName;
    @JsonProperty("in-discards")
    private int inDiscards;
    @JsonProperty("in-errors")
    private int inErrors;
    @JsonProperty("in-multicast-pkts")
    @JsonAlias("in-multicast-packets")
    private int inMulticastPackets;
    @JsonProperty("in-unknown-protocol-discards")
    private int inUnknownProtocolDiscards;
    @JsonProperty("out-discards")
    private int outDiscards;
    @JsonProperty("out-errors")
    private int outErrors;
    @JsonProperty("out-multicast-pkts")
    @JsonAlias("out-multicast-packets")
    private int outMulticastPackets;
    @JsonProperty("in-packets")
    private int inPackets;
    @JsonProperty("out-packets")
    private int outPackets;

}
