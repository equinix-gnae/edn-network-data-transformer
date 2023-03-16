package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import com.equinix.edn.networkdatatransformer.constants.DruidTagsConstants;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiTag;

public class GnmiMessageUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    //function to convert gnmi tags to druid tags
    private static LinkedHashMap<String, Object> convertGnmiTagsToDruidTags(GnmiTag tags) {
        LinkedHashMap<String, Object> druidTags = new LinkedHashMap<>();
        druidTags.put(DruidTagsConstants.IBX, tags.getIbx());
        druidTags.put(DruidTagsConstants.METRO, tags.getMetro());
        druidTags.put(DruidTagsConstants.REGION, tags.getRegion());
        druidTags.put(DruidTagsConstants.SOURCE, tags.getSource());
        druidTags.put(DruidTagsConstants.INTERFACE_NAME, tags.getInterfaceName());
        druidTags.put(DruidTagsConstants.NETWORK_INSTANCE_NAME, tags.getNetworkInstanceName());
        druidTags.put(DruidTagsConstants.SUBSCRIPTION_NAME, tags.getSubscriptionName());
        druidTags.put(DruidTagsConstants.NEIGHBOR_ADDRESS, tags.getNeighborAddress());

        druidTags.entrySet().removeIf(entry -> entry.getValue() == null);

        return druidTags;
    }

    public static DruidMessage createDruidMessage(GnmiMessage gnmiMessage, String dataPointName, Long metricValue) {
        long milliseconds = gnmiMessage.getTimestamp() / 1000000L;
        return DruidMessage.builder()
                           .dataPointName(dataPointName)
                           .eventTimeIso(GnmiMessageUtils.convertTimestampToISO(milliseconds))
                           .eventTimestamp(gnmiMessage.getTimestamp())
                           .eventProcessedTimeIso(GnmiMessageUtils.convertTimestampToISO(Instant.now().toEpochMilli()))
                           .eventProcessedTime(Instant.now().toEpochMilli())
                           .tags(GnmiMessageUtils.convertGnmiTagsToDruidTags(gnmiMessage.getTags()))
                           .metricValue(metricValue).build();
    }


    //function to convert timestamp to ISO format
    private static String convertTimestampToISO(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return formatter.format(instant);
    }
}
