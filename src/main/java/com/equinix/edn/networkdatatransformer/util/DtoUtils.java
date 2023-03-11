package com.equinix.edn.networkdatatransformer.util;

import java.util.LinkedHashMap;

import com.equinix.edn.networkdatatransformer.dto.GnmiMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DtoUtils {

    //function to convert LinkedHashMap to GNMI Message Object
    public static GnmiMessage convertLinkedHashMapToGnmiMessage(LinkedHashMap<String, Object> linkedHashMap) {
        ObjectMapper objectMapper = createObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(linkedHashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        GnmiMessage gnmiMessage = null;
        try {
            gnmiMessage = objectMapper.readValue(jsonString, GnmiMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return gnmiMessage;
    }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
