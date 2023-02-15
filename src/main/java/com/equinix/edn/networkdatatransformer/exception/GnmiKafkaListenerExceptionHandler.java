package com.equinix.edn.networkdatatransformer.exception;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GnmiKafkaListenerExceptionHandler implements ConsumerAwareListenerErrorHandler {

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
        log.error("[GNMI] Handling error in Kafka Listener. Error Message: {}", e.getMessage());
        consumer.commitSync();
        return null;
    }
}
