package com.equinix.edn.networkdatatransformer.config.kafka;

import java.util.Map;

import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SimpleKafkaHeaderMapper;
import org.springframework.messaging.MessageHeaders;

/**
 * A header mapper that maps headers directly; for outbound,
 * byte[] and String headers are mapped; for inbound, all headers are mapped
 * as String.
 * See {@link SimpleKafkaHeaderMapper} and {@link AsStringKafkaHeaderMapper} for more details.
 */
public final class AsStringKafkaHeaderMapper extends SimpleKafkaHeaderMapper {
    /**
     * Construct an instance with default header patterns for outbound headers;
     * all inbound headers are mapped. The default pattern list is
     * {@code "!id", "!timestamp" and "*"}. In addition, most of the headers in
     * {@link KafkaHeaders} are never mapped as headers since they represent data in
     * consumer/producer records.
     */
    public AsStringKafkaHeaderMapper() {
        super("!" + MessageHeaders.ID,
                "!" + MessageHeaders.TIMESTAMP,
                "*");
        setMapAllStringsOut(true);
    }

    @Override
    public void toHeaders(Headers source, Map<String, Object> target) {
        source.forEach(
            header -> target.put(header.key(), new String(header.value(), getCharset()).replaceAll("^\"|\"$", "")));
    }
}
