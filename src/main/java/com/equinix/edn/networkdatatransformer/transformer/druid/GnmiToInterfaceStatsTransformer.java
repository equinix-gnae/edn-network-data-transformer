package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.equinix.edn.networkdatatransformer.dto.DataPointName;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GnmiToInterfaceStatsTransformer {

    /*
    Example Payload:
       {
  "source": "site1-leaf2.lab.equinix.com:9339",
  "subscription-name": "default-1675286922",
  "timestamp": 1675286938710559000,
  "time": "2023-02-01T13:28:58.710559-08:00",
  "prefix": "openconfig:interfaces",
  "updates": [
    {
      "Path": "interface[name=swp70]/state/counters/in-octets",
      "values": {
        "interface/state/counters/in-octets": 3614134
      }
    },
    {
      "Path": "interface[name=swp70]/state/counters/in-discards",
      "values": {
        "interface/state/counters/in-discards": 0
      }
    },
    {
      "Path": "interface[name=swp70]/state/counters/in-errors",
      "values": {
        "interface/state/counters/in-errors": 0
      }
    },
    {
      "Path": "interface[name=swp70]/state/counters/out-octets",
      "values": {
        "interface/state/counters/out-octets": 3617676
      }
    },
    {
      "Path": "interface[name=swp70]/state/counters/out-discards",
      "values": {
        "interface/state/counters/out-discards": 0
      }
    },
    {
      "Path": "interface[name=swp70]/state/counters/out-errors",
      "values": {
        "interface/state/counters/out-errors": 0
      }
    }
  ]
}
     */
//    public List<DruidMessage> toInterfaceStatsForDruid(GnmiMessage gnmiMessage) {
//
//        return gnmiMessage.getUpdates().stream()
//                          .flatMap(gnmiDataPoint -> gnmiDataPoint.getValues().entrySet().stream())
//                          .map(entry -> {
//                              DataPointName druidDataPointName = DataPointName.fromValue(entry.getKey());
//                              if (druidDataPointName != null) {
//                                  return DruidMessage.builder()
//                                                     .dataPointName(druidDataPointName)
//                                                     .router(gnmiMessage.getSource())
//                                                     .value(entry.getValue())
//                                                     .eventId(gnmiMessage.getEventId())
//                                                     .networkInstance(gnmiMessage.getPrefix())
//                                                     .build();
//                              } else {
//                                  return null;
//                              }
//                          })
//                          .filter(Objects::nonNull)
//                          .collect(Collectors.toList());
//
//    }
}
