package com.equinix.edn.networkdatatransformer.transformer.druid;

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
public class NetworkInstanceDataTransformer implements GnmiMessageTransformer{

    @Override
    public List<DruidMessage> transform(GnmiMessage gnmiMessage) {
        List<DruidMessage> druidMessageList = new LinkedList<>();
        gnmiMessage.getValues().getBgpStatsGnmiSensorMetricMap().forEach((key, value) -> {
            if (null != value) {
                DruidMessage druidMessage = GnmiMessageUtils.createDruidMessage(gnmiMessage, key, value);
                druidMessageList.add(druidMessage);
            }
        });

        gnmiMessage.getValues().getBgpStateSensorMap().forEach((key, value) -> {
            if (null != value) {
                DruidMessage druidMessage = GnmiMessageUtils.createDruidMessage(gnmiMessage, key, null);
                druidMessage.setState(value);
                druidMessageList.add(druidMessage);
            }
        });

        return druidMessageList;
    }

    @Override
    public GnmiMessageType getMessageType() {
        return GnmiMessageType.NETWORK_INSTANCE_DATA;
    }
}
