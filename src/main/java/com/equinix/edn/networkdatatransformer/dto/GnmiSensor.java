package com.equinix.edn.networkdatatransformer.dto;

import java.util.HashMap;
import java.util.Map;

import com.equinix.edn.networkdatatransformer.constants.GnmiSensorConstants;
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
    private Long carrierTransitions;
    @JsonProperty("high-speed")
    private Long highSpeed;
    @JsonProperty("in-broadcast-pkts")
    @JsonAlias("in-broadcast-packets")
    private Long inBroadcastPkts;
    @JsonProperty("in-octets")
    private Long inOctets;
    @JsonProperty("in-pkts")
    @JsonAlias("in-packets")
    private Long inPkts;
    @JsonProperty("in-unicast-pkts")
    @JsonAlias("in-unicast-packets")
    private Long inUnicastPkts;
    @JsonProperty("init_time")
    private Long initTime;
    @JsonProperty("out-broadcast-pkts")
    @JsonAlias("out-broadcast-packets")
    private Long outBroadcastPkts;
    @JsonAlias({"out-multicast-packets", "out-multicast-pkts"})
    private Long outMulticastPkts;
    @JsonProperty("out-octets")
    private Long outOctets;
    @JsonProperty("out-pkts")
    @JsonAlias("out-packets")
    private Long outPkts;
    @JsonProperty("out-unicast-pkts")
    @JsonAlias("out-unicast-packets")
    private Long outUnicastPkts;
    @JsonProperty("parent_ae_name")
    private String parentAeName;
    @JsonProperty("in-discards")
    private Long inDiscards;
    @JsonProperty("in-errors")
    private Long inErrors;
    @JsonProperty("in-multicast-pkts")
    @JsonAlias("in-multicast-packets")
    private Long inMulticastPackets;
    @JsonProperty("in-unknown-protocol-discards")
    private Long inUnknownProtocolDiscards;
    @JsonProperty("out-discards")
    private Long outDiscards;
    @JsonProperty("out-errors")
    private Long outErrors;
    @JsonProperty("out-multicast-pkts")
    @JsonAlias("out-multicast-packets")
    private Long outMulticastPackets;
    @JsonProperty("in-packets")
    private Long inPackets;
    @JsonProperty("out-packets")
    private Long outPackets;
    @JsonProperty("oper-status")
    private String operStatus;

    public Map<String, Long> getInterfaceStatsGnmiSensorMetricMap(){
        Map<String, Long> sensorMap = new HashMap<>();
        sensorMap.put(GnmiSensorConstants.CARRIER_TRANSITIONS, this.carrierTransitions);
        sensorMap.put(GnmiSensorConstants.HIGH_SPEED, this.highSpeed);
        sensorMap.put(GnmiSensorConstants.IN_BROADCAST_PKTS, this.inBroadcastPkts);
        sensorMap.put(GnmiSensorConstants.IN_OCTETS, this.inOctets);
        sensorMap.put(GnmiSensorConstants.IN_PACKETS, this.inPkts);
        sensorMap.put(GnmiSensorConstants.IN_UNICAST_PKTS, this.inUnicastPkts);
        sensorMap.put(GnmiSensorConstants.INIT_TIME, this.initTime);
        sensorMap.put(GnmiSensorConstants.OUT_BROADCAST_PKTS, this.outBroadcastPkts);
        sensorMap.put(GnmiSensorConstants.OUT_MULTICAST_PKTS, this.outMulticastPkts);
        sensorMap.put(GnmiSensorConstants.OUT_OCTETS, this.outOctets);
        sensorMap.put(GnmiSensorConstants.OUT_PACKETS, this.outPkts);
        sensorMap.put(GnmiSensorConstants.OUT_UNICAST_PKTS, this.outUnicastPkts);
        sensorMap.put(GnmiSensorConstants.IN_DISCARDS, this.inDiscards);
        sensorMap.put(GnmiSensorConstants.IN_ERRORS, this.inErrors);
        sensorMap.put(GnmiSensorConstants.IN_MULTICAST_PKTS, this.inMulticastPackets);
        sensorMap.put(GnmiSensorConstants.IN_UNKNOWN_PROTOCOL_DISCARDS, this.inUnknownProtocolDiscards);
        sensorMap.put(GnmiSensorConstants.OUT_DISCARDS, this.outDiscards);
        sensorMap.put(GnmiSensorConstants.OUT_ERRORS, this.outErrors);
        return sensorMap;
    }





}
