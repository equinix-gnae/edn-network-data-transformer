package com.equinix.edn.networkdatatransformer.transformer.druid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DruidTransformerMapBuilder {

    private final Map<DruidDataSourceType, DruidTransformer> druidTransformerMap = new HashMap<>();

    public DruidTransformerMapBuilder(List<DruidTransformer> druidTransformers) {
        for (DruidTransformer transformer : druidTransformers) {
            druidTransformerMap.put(transformer.getDataSourceType(), transformer);
        }
    }

    public DruidTransformer getTransformer(DruidDataSourceType dataSourceType) {
        return druidTransformerMap.get(dataSourceType);
    }
}
