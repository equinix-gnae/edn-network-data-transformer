package com.equinix.edn.networkdatatransformer.dto;

import java.util.HashMap;
import java.util.Map;

public enum DataPointName {
    //The operational status of each network interface
    NETWORK_INTERFACE_STATUS,
    //The number of bytes received on the network interface
    NETWORK_INTERFACE_BYTES_IN,
    //The number of bytes transmitted on the network interface
    NETWORK_INTERFACE_BYTES_OUT,
    //The number of packets received on the network interface
    NETWORK_INTERFACE_PACKETS_IN,
    //The number of packets transmitted on the network interface
    NETWORK_INTERFACE_PACKETS_OUT,
    //This metric measures the number of octets (8-bit bytes) received on the network interface. It includes both
    // data and protocol overhead, and is a key indicator of the amount of traffic flowing through the interface.
    // This metric can help identify network bottlenecks, monitor network performance, and troubleshoot network
    // issues related to data transfer.
    NETWORK_INTERFACE_OCTETS_IN,
    //This metric measures the number of octets (8-bit bytes) transmitted on the network interface. Like
    // NETWORK_INTERFACE_OCTETS_IN, it includes both data and protocol overhead, and can be used to monitor network
    // performance, identify bottlenecks, and troubleshoot issues related to data transfer. This metric is often used
    // in conjunction with other interface statistics, such as packet counts and error rates, to provide a more
    // complete picture of network performance.
    NETWORK_INTERFACE_OCTETS_OUT,
    //The number of input errors on the network interface
    NETWORK_INTERFACE_ERRORS_IN,
    //The number of output errors on the network interface
    NETWORK_INTERFACE_ERRORS_OUT,
    //The number of discarded input packets on the network interface
    NETWORK_INTERFACE_DISCARDS_IN,
    //The number of discarded output packets on the network interface
    NETWORK_INTERFACE_DISCARDS_OUT,
    //The percentage of network bandwidth being used by the network interface
    NETWORK_INTERFACE_UTILIZATION,
    //The rate of incoming traffic on the network interface, measured in bits or bytes per second
    NETWORK_INTERFACE_INPUT_RATE,
    //The rate of outgoing traffic on the network interface, measured in bits or bytes per second
    NETWORK_INTERFACE_OUTPUT_RATE;

    private static final Map<String, DataPointName> DATA_POINT_NAME_MAP = new HashMap<>();

    static {
        DATA_POINT_NAME_MAP.put("oper-state", NETWORK_INTERFACE_STATUS );
        DATA_POINT_NAME_MAP.put("state/oper-status", NETWORK_INTERFACE_STATUS);
        DATA_POINT_NAME_MAP.put("in-discards",NETWORK_INTERFACE_DISCARDS_IN );
        DATA_POINT_NAME_MAP.put("interface/state/counters/in-discards",NETWORK_INTERFACE_DISCARDS_IN );
        DATA_POINT_NAME_MAP.put("out-discards", NETWORK_INTERFACE_DISCARDS_OUT);
        DATA_POINT_NAME_MAP.put("interface/state/counters/out-discards", NETWORK_INTERFACE_DISCARDS_OUT);
        DATA_POINT_NAME_MAP.put("in-errors", NETWORK_INTERFACE_ERRORS_IN);
        DATA_POINT_NAME_MAP.put("interface/state/counters/in-errors", NETWORK_INTERFACE_ERRORS_IN);
        DATA_POINT_NAME_MAP.put("out-errors", NETWORK_INTERFACE_ERRORS_OUT);
        DATA_POINT_NAME_MAP.put("interface/state/counters/out-errors",NETWORK_INTERFACE_ERRORS_OUT );
        DATA_POINT_NAME_MAP.put("in-octets", NETWORK_INTERFACE_OCTETS_IN);
        DATA_POINT_NAME_MAP.put("out-octets", NETWORK_INTERFACE_OCTETS_OUT);
        DATA_POINT_NAME_MAP.put("interface/state/counters/out-octets", NETWORK_INTERFACE_OCTETS_OUT);
        DATA_POINT_NAME_MAP.put("in-packets", NETWORK_INTERFACE_PACKETS_IN);
        DATA_POINT_NAME_MAP.put("out-packets", NETWORK_INTERFACE_PACKETS_OUT);
    }

    public static DataPointName fromValue(String value) {
       return DATA_POINT_NAME_MAP.get(value);
    }
}
