package com.equinix.edn.networkdatatransformer.config.kafka;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.equinix.edn.networkdatatransformer.dto.KafkaTopic;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.converter.MessagingMessageConverter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Configuration
@ConfigurationProperties("edn.kafka")
@Slf4j
public class KafkaConfig {


    private static final String TOPIC_GROUP_RAW_DATA = "raw-data";
    private static final String TOPIC_GROUP_NORMALIZED_DATA = "normalized-data";
    private final ConfigurableBeanFactory beanFactory;
    private String brokerAddress;
    private Map<String, KafkaTopic> topic;
    private String consumerGroup;
    private Integer concurrency;

    public KafkaConfig(ConfigurableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    public void logConfiguration() {
        log.info("[Kafka Configuration] Broker Address:{}, topic:{}", brokerAddress, topic);
    }

    @PostConstruct
    public void createTopics() {
        List<KafkaTopic> topics = getTopics();

        topics.forEach(kafkaTopic -> {
            if (!beanFactory.containsSingleton(kafkaTopic.getTopicName())) {
                beanFactory.registerSingleton(kafkaTopic.getTopicName(), new NewTopic(
                        kafkaTopic.getTopicName(), kafkaTopic.getPartitions(),
                        kafkaTopic.getReplicas()));
            }
        });

    }

    private List<KafkaTopic> getTopics() {
        List<KafkaTopic> topicList = new LinkedList<>();
        KafkaTopic rawDataTopicConfig = topic.get(TOPIC_GROUP_RAW_DATA);
        topicList.add(rawDataTopicConfig);
        KafkaTopic normalizedDataTopicConfig = topic.get(TOPIC_GROUP_NORMALIZED_DATA);
        topicList.add(normalizedDataTopicConfig);
        return topicList;
    }

    @Bean
    public MessagingMessageConverter simpleMapperConverter() {
        MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
        messagingMessageConverter.setHeaderMapper(new AsStringKafkaHeaderMapper());
        return messagingMessageConverter;
    }

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
        return new KafkaAdmin(configs);
    }
}
