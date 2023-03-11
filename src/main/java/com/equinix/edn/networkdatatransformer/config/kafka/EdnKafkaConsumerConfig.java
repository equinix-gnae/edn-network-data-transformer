package com.equinix.edn.networkdatatransformer.config.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.MessagingMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;


//EdnKafkaConsumerConfig class is used to configure the Kafka consumer

@Configuration
public class EdnKafkaConsumerConfig {
    private final EdnKafkaConfig ednKafkaConfig;
    private final MessagingMessageConverter simpleMapperConverter;

    public EdnKafkaConsumerConfig(EdnKafkaConfig ednKafkaConfig, MessagingMessageConverter simpleMapperConverter) {
        this.ednKafkaConfig = ednKafkaConfig;
        this.simpleMapperConverter = simpleMapperConverter;
    }

    //This function is used to build common properties for Kafka consumer
    // @return Map<String, Object> - Map of common properties for Kafka consumer
    private Map<String, Object> commonProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ednKafkaConfig.getBrokerAddress());
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, Integer.toString(Integer.MAX_VALUE));
        return props;
    }

    //This function adds additional properties to the common properties for network data transformer consumer
    //Use TypeReference to deserialize the JSON message to List of GnmiMessage

    private DefaultKafkaConsumerFactory<Object, Object> networDataTransformerConsumerProps() {

        Map<String, Object> properties = commonProperties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                ErrorHandlingDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                ErrorHandlingDeserializer.class);
        properties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        properties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, List.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, ednKafkaConfig.getConsumerGroup());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    //Create a function to instantiate ConcurrentKafkaListenerContainerFactory for network data transformer consumer.
    // This function is used to configure the Kafka consumer.
    // This function configures error handler using SeekToCurrentErrorHandler
    // @param configurer - ConcurrentKafkaListenerContainerFactoryConfigurer
    // @param kafkaConsumerFactory - ConsumerFactory<Object, Object>
    // @param template - KafkaTemplate<Object, Object>
    // @return ConcurrentKafkaListenerContainerFactory<?, ?> - ConcurrentKafkaListenerContainerFactory for network
    // data transformer consumer
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> networDataTransformerListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(1);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setConsumerFactory(networDataTransformerConsumerProps());
        factory.setMessageConverter(simpleMapperConverter);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(0L, 1L)));
        return factory;
    }


}
