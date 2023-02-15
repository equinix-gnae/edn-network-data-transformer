package com.equinix.edn.networkdatatransformer.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.converter.MessagingMessageConverter;

@Configuration
public class KafkaConsumerConfig {
    private final KafkaConfig kafkaConfig;
    private final MessagingMessageConverter simpleMapperConverter;

    public KafkaConsumerConfig(KafkaConfig kafkaConfig, MessagingMessageConverter simpleMapperConverter) {
        this.kafkaConfig = kafkaConfig;
        this.simpleMapperConverter = simpleMapperConverter;
    }

    private Map<String, Object> commonProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBrokerAddress());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, Integer.toString(Integer.MAX_VALUE));
        return props;
    }

    private DefaultKafkaConsumerFactory<String, String> networDataTransformerConsumerProps() {

        Map<String, Object> properties = commonProperties();
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getConsumerGroup());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    networkDataTransformerConsumerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(networDataTransformerConsumerProps());
        factory.setConcurrency(kafkaConfig.getConcurrency());
        factory.setMessageConverter(simpleMapperConverter);
        return factory;
    }
}