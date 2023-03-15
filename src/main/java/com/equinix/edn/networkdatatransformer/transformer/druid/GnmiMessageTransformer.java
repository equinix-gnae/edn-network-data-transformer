package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.List;

import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessageType;

public interface GnmiMessageTransformer {
    List<DruidMessage> transform(GnmiMessage gnmiMessage);
    GnmiMessageType getMessageType();
}
