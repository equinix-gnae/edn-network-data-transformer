package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.List;

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

        return null;
    }

    @Override
    public GnmiMessageType getMessageType() {
        return GnmiMessageType.NETWORK_INSTANCE_DATA;
    }
}
