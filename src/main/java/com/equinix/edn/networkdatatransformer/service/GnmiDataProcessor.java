package com.equinix.edn.networkdatatransformer.service;

import java.util.List;

import com.equinix.edn.networkdatatransformer.dto.DruidMessage;
import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.equinix.edn.networkdatatransformer.transformer.druid.GnmiToInterfaceStatsTransformer;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GnmiDataProcessor {

    private final GnmiToInterfaceStatsTransformer gnmiToInterfaceStatsTransformer;

    public GnmiDataProcessor(GnmiToInterfaceStatsTransformer gnmiToInterfaceStatsTransformer) {
        this.gnmiToInterfaceStatsTransformer = gnmiToInterfaceStatsTransformer;
    }

    public void process(GnmiMessage gnmiMessage) {

      //  List<DruidMessage> druidMessageList = gnmiToInterfaceStatsTransformer.toInterfaceStatsForDruid(gnmiMessage);
    }
}
