package com.equinix.edn.networkdatatransformer.dto;

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
    NETWORK_INTERFACE_OUTPUT_RATE
}
