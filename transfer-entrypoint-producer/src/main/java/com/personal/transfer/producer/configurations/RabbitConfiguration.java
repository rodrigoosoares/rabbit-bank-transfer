package com.personal.transfer.producer.configurations;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.personal.transfer.producer.configurations.ConfigurationConstants.TOPICS_NAME_PATTERN;

@Configuration
public class RabbitConfiguration {


    private final AmqpAdmin amqpAdmin;
    private final QueueData queueData;

    @Autowired
    public RabbitConfiguration(final AmqpAdmin amqpAdmin, final QueueData queueData) {

        this.amqpAdmin = amqpAdmin;
        this.queueData = queueData;
    }

    @Bean
    public void createQueues() {

        queueData.getOperations().forEach(operation ->
                amqpAdmin.declareQueue(createTopicByOperation(operation)));
    }

    private Queue createTopicByOperation(final String operation) {

        return new Queue(getTopicName(operation, queueData.getPrefix()));
    }

    private String getTopicName(final String operation, final String prefix) {

        return String.format(TOPICS_NAME_PATTERN, prefix, operation);
    }

}
