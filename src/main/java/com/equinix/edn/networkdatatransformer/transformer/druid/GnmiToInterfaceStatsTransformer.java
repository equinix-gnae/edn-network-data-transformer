package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.equinix.edn.networkdatatransformer.dto.DataPointName;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiDataPoint;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GnmiToInterfaceStatsTransformer {

    public List<DruidMessage> toInterfaceStatsForDruid(GnmiMessage gnmiMessage) {

        return gnmiMessage.getUpdates().stream()
                          .flatMap(gnmiDataPoint -> gnmiDataPoint.getValues().entrySet().stream())
                          .map(entry -> {
                              DataPointName druidDataPointName = DataPointName.fromValue(entry.getKey());
                              if (druidDataPointName != null) {
                                  return DruidMessage.builder()
                                                     .dataPointName(druidDataPointName)
                                                     .router(gnmiMessage.getSource())
                                                     .value(entry.getValue())
                                                     .eventId(gnmiMessage.getEventId())
                                                     .networkInstance(gnmiMessage.getPrefix())
                                                     .build();
                              } else {
                                  return null;
                              }
                          })
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());

    }
}
