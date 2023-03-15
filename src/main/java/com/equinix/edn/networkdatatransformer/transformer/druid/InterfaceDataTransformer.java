package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import com.equinix.edn.networkdatatransformer.constants.GnmiSensorConstants;
import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessageType;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InterfaceDataTransformer implements GnmiMessageTransformer{

    @Override
    public List<DruidMessage> transform(GnmiMessage gnmiMessage) {
        List<DruidMessage> druidMessageList = new LinkedList<>();
        gnmiMessage.getValues().getInterfaceStatsGnmiSensorMetricMap().forEach((key, value) -> {
            if (null != value) {
                DruidMessage druidMessage = createDruidMessageBuilder(gnmiMessage, key, value);
                druidMessageList.add(druidMessage);
            }
        });

        if (null != gnmiMessage.getValues().getOperStatus()) {
            DruidMessage druidMessage = createDruidMessageBuilder(gnmiMessage, GnmiSensorConstants.OPER_STATUS, null);
            druidMessage.setState(gnmiMessage.getValues().getOperStatus());
            druidMessageList.add(druidMessage);
        }

        return druidMessageList;
    }

    private DruidMessage createDruidMessageBuilder(GnmiMessage gnmiMessage, String dataPointName, Long value) {
        long milliseconds = gnmiMessage.getTimestamp() / 1000000L;
        return DruidMessage.builder()
                           .dataPointName(dataPointName)
                           .eventTimeIso(GnmiMessageUtils.convertTimestampToISO(milliseconds))
                           .eventTimestamp(gnmiMessage.getTimestamp())
                           .eventProcessedTimeIso(GnmiMessageUtils.convertTimestampToISO(Instant.now().toEpochMilli()))
                           .eventProcessedTime(Instant.now().toEpochMilli())
                           .tags(GnmiMessageUtils.convertGnmiTagsToDruidTags(gnmiMessage.getTags()))
                           .metricValue(value).build();
    }

    @Override
    public GnmiMessageType getMessageType() {
        return GnmiMessageType.INTERFACE_DATA;
    }
}
