package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.equinix.edn.networkdatatransformer.constants.DruidTagsConstants;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiTag;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GnmitoDruidEventTransformer implements DruidTransformer<List<DruidMessage>>{

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    //function to transform gnmi message to list of druid events
    @Override
    public List<DruidMessage> transform(GnmiMessage gnmiMessage) {
        List<DruidMessage> druidMessageList = new LinkedList<>();

        gnmiMessage.getValues().getGnmiSensorMetricMap().forEach((key, value) -> {
            if (null != value) {
                DruidMessage druidMessage = DruidMessage.builder()
                                                        .dataPointName(key)
                                                        //converting nanoseconds to milliseconds
                                                        .eventTimeIso(convertTimestampToISO(gnmiMessage.getTimestamp()/1000000L))
                                                        .eventTimestamp(gnmiMessage.getTimestamp())
                                                        .eventProcessedTimeIso(
                                                                convertTimestampToISO(Instant.now().toEpochMilli()))
                                                        .eventProcessedTime(Instant.now().toEpochMilli())
                                                        .tags(convertGnmiTagsToDruidTags(gnmiMessage.getTags()))
                                                        .metricValue(value)
                                                        .build();
                druidMessageList.add(druidMessage);
            }
        });

        return druidMessageList;
    }

    @Override
    public DruidDataSourceType getDataSourceType() {
        return DruidDataSourceType.GNMI;
    }

    //function to convert gnmi tags to druid tags
    private LinkedHashMap<String, Object> convertGnmiTagsToDruidTags(GnmiTag tags) {
        LinkedHashMap<String, Object> druidTags = new LinkedHashMap<>();
        druidTags.put(DruidTagsConstants.IBX, tags.getIbx());
        druidTags.put(DruidTagsConstants.METRO, tags.getMetro());
        druidTags.put(DruidTagsConstants.REGION, tags.getRegion());
        druidTags.put(DruidTagsConstants.SOURCE, tags.getSource());
        druidTags.put(DruidTagsConstants.INTERFACE_NAME, tags.getInterfaceName());
        druidTags.put(DruidTagsConstants.SUBSCRIPTION_NAME, tags.getSubscriptionName());

        druidTags.entrySet().removeIf(entry -> entry.getValue() == null);

        return druidTags;
    }

    //function to convert timestamp to ISO format
    private String convertTimestampToISO(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return formatter.format(instant);
    }
}
