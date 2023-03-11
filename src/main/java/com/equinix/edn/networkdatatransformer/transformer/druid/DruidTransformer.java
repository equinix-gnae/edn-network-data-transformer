package com.equinix.edn.networkdatatransformer.transformer.druid;

import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;

public interface DruidTransformer<T> {
    T transform(GnmiMessage gnmiMessage);

    DruidDataSourceType getDataSourceType();
}
