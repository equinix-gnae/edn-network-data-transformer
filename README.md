# EDN Network Data Transformer
This microservice is designed to collect data from network routers, transform and normalize it, and persist it in Druid for analysis and reporting purposes. The com.equinix.edn.networkdatatransformer.service is built using Spring Boot and can be easily configured and deployed in a variety of environments.
## Druid Schema
The Druid schema is defined in the com.equinix.edn.networkdatatransformer.dto.DruidMessage class. 
## Data Sources
Name: edn_network_data
Dimensions: event_id, timestamp, timestamp_iso, processed_at, processed_at_iso, datapoint_name, tags, metric_value, state
Metrics: metric_value

## Sample GNMIc Data from Juniper, Nokia, and Arrcus routers
## Interface Stats
### Junos
Interface Stats
```
[
    {
        "name": "junos-interface",
        "timestamp": 1678308514964000000,
        "tags": {
            "ibx": "sv5",
            "interface_name": "xe-0/0/1",
            "metro": "SV",
            "source": "172.20.98.246:32767",
            "subscription-name": "junos-interface"
        },
        "values": {
            "carrier-transitions": 3,
            "high-speed": 10000,
            "in-broadcast-pkts": 4,
            "in-octets": 10164,
            "in-pkts": 169,
            "in-unicast-pkts": 165,
            "init_time": 1647996238,
            "out-broadcast-pkts": 171,
            "out-multicast-pkts": 43758,
            "out-octets": 18206510262506,
            "out-pkts": 165513649410,
            "out-unicast-pkts": 165513605480,
            "parent_ae_name": ""
        }
    }
]
```

 Nokia
```
[
    {
        "name": "nokia-interface",
        "timestamp": 1677141752251473700,
        "tags": {
            "ibx": "sv5",
            "metro": "SV",
            "port_port-id": "A/4",
            "source": "10.202.10.180:57400",
            "subscription-name": "nokia-interface"
        },
        "values": {
            "in-broadcast-packets": "0",
            "in-discards": "0",
            "in-errors": "0",
            "in-multicast-packets": "0",
            "in-octets": "0",
            "in-packets": "0",
            "in-unicast-packets": "0",
            "in-unknown-protocol-discards": "0",
            "out-broadcast-packets": "0",
            "out-discards": "0",
            "out-errors": "0",
            "out-multicast-packets": "0",
            "out-octets": "0",
            "out-packets": "0",
            "out-unicast-packets": "0"
        }
    }
]
```

### Arrcus
```
[
    {
        "name": "arrcus-interface",
        "timestamp": 1678307947928291000,
        "tags": {
            "ibx": "sv5",
            "interface_name": "swp30",
            "metro": "SV",
            "source": "10.196.21.62:9339",
            "subscription-name": "arrcus-interface"
        },
        "values": {
            "in-discards": 0,
            "in-errors": 0,
            "in-octets": 0,
            "out-discards": 0,
            "out-errors": 0,
            "out-octets": 0
        }
    }
]
```
### Drivenet
```
[
    {
        "name": "drivenets-interface",
        "timestamp": 1680899328201643639,
        "tags": {
            "ibx": "sv3",
            "interface_name": "vrfr1-0/0/0",
            "metro": "SV",
            "platform": "drivenets",
            "role": "leaf-node",
            "source": "sp3-ngn.gv51.lab.equinix.com",
            "subscription-name": "drivenets-interface"
        },
        "values": {
            "rx-octets": 0,
            "rx-packets": 0,
            "tx-octets": 0,
            "tx-packets": 0
        }
    }
]
```
## Interface State
### Junos
[
{
"name": "junos-interface",
"timestamp": 1677141727556676900,
"tags": {
"ibx": "sv5",
"interface_name": "fxp0",
"metro": "SV",
"source": "172.20.98.246:32767",
"subscription-name": "junos-interface"
},
"values": {
"oper-status": "up"
}
}
]

### Nokia
[
{
"name": "nokia-interface",
"timestamp": 1677141752251474400,
"tags": {
"ibx": "sv5",
"metro": "SV",
"port_port-id": "A/4",
"source": "10.202.10.180:57400",
"subscription-name": "nokia-interface"
},
"values": {
"oper-state": "down"
}
}
]

### Arrcus
[
{
"name": "arrcus-interface",
"timestamp": 1678307944617930000,
"tags": {
"ibx": "sv5",
"interface_name": "loopback0",
"metro": "SV",
"source": "10.196.21.62:9339",
"subscription-name": "arrcus-interface"
},
"values": {
"oper-status": "up"
}
}
]

## BGP Session State
### Junos
[
{
"name": "junos-network-instance",
"timestamp": 1678308933786535200,
"tags": {
"ibx": "sv5",
"metro": "SV",
"neighbor_neighbor-address": "206.197.169.1",
"network-instance_name": "master",
"source": "172.20.98.246:32767",
"subscription-name": "junos-network-instance"
},
"values": {
"session-state": "ESTABLISHED"
}
}
]

### Nokia
[
{
"name": "nokia-network-instance",
"timestamp": 1677141752002550000,
"tags": {
"ibx": "sv5",
"metro": "SV",
"neighbor_ip-address": "64.191.213.17",
"source": "10.202.10.180:57400",
"subscription-name": "nokia-network-instance",
"vprn_service-name": "RI-VRF-200051-5174-sitautouse-DC"
},
"values": {
"session-state": "Established"
}
}
]



## BGP Admin State
### Junos
[
{
"name": "junos-network-instance",
"timestamp": 1678308936431241500,
"tags": {
"ibx": "sv5",
"metro": "SV",
"neighbor_neighbor-address": "192.85.1.60",
"network-instance_name": "RI-VRF-NFV-13454-00015-CUSTOMER1-DC",
"source": "172.20.98.246:32767",
"subscription-name": "junos-network-instance"
},
"values": {
"session-admin-status": "STOP"
}
}
]

## BGP Route Counts
### Nokia
[
{
"name": "nokia-network-instance",
"timestamp": 1677141752365534200,
"tags": {
"ibx": "sv5",
"metro": "SV",
"source": "10.196.67.5:57400",
"subscription-name": "nokia-network-instance",
"vprn_service-name": "9002"
},
"values": {
"active-routes": 4,
"available-routes": 4
}
}
]

 

