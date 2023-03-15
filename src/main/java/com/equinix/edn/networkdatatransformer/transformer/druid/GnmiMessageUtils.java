package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import com.equinix.edn.networkdatatransformer.constants.DruidTagsConstants;
import com.equinix.edn.networkdatatransformer.dto.GnmiTag;

public class GnmiMessageUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
    //function to convert gnmi tags to druid tags
    public static LinkedHashMap<String, Object> convertGnmiTagsToDruidTags(GnmiTag tags) {
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
    public static String convertTimestampToISO(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return formatter.format(instant);
    }
}
