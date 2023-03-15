package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GnmitoDruidEventTransformer implements DruidTransformer<List<DruidMessage>>{

    private final Map<GnmiMessageType, GnmiMessageTransformer> transformerMap;

    public GnmitoDruidEventTransformer(List<GnmiMessageTransformer> transformers) {
        this.transformerMap = transformers.stream()
                                          .collect(Collectors.toMap(GnmiMessageTransformer::getMessageType,
                                                  Function.identity()));
    }

    //function to transform gnmi message to list of druid events
    @Override
    public List<DruidMessage> transform(GnmiMessage gnmiMessage) {
        GnmiMessageTransformer transformer = transformerMap.get(gnmiMessage.getName());
        if (transformer == null) {
            throw new IllegalArgumentException("No transformer found for message type: " + gnmiMessage.getName());
        }
        return transformer.transform(gnmiMessage);
    }

    @Override
    public DruidDataSourceType getDataSourceType() {
        return DruidDataSourceType.GNMI;
    }



}
