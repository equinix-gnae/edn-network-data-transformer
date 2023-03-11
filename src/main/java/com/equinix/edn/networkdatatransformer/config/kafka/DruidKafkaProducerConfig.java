package com.equinix.edn.networkdatatransformer.config.kafka;

import java.util.HashMap;
import java.util.Map;

import com.equinix.edn.networkdatatransformer.dto.DruidMessage;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DruidKafkaProducerConfig {

    private final EdnKafkaConfig ednKafkaConfig;

    public DruidKafkaProducerConfig(EdnKafkaConfig ednKafkaConfig) {
        this.ednKafkaConfig = ednKafkaConfig;
    }

    @Bean
    public ProducerFactory<String, DruidMessage> producerFactory() {

        log.info("In Kafka Producer Factory");

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ednKafkaConfig.getBrokerAddress());
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, DruidMessage> kafkaTemplate() {
        KafkaTemplate<String, DruidMessage> kafkaTemplate = new KafkaTemplate<>(producerFactory());

        MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
        DefaultKafkaHeaderMapper defaultKafkaHeaderMapper = new DefaultKafkaHeaderMapper();
        defaultKafkaHeaderMapper.setEncodeStrings(true);
        messagingMessageConverter.setHeaderMapper(defaultKafkaHeaderMapper);

        kafkaTemplate.setMessageConverter(messagingMessageConverter);

        return kafkaTemplate;
    }
}
