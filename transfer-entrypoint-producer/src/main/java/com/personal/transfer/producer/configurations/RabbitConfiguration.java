package com.personal.transfer.producer.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    private final CachingConnectionFactory connectionFactory;
    private final QueueData queueData;

    @Autowired
    public RabbitConfiguration(final CachingConnectionFactory connectionFactory, final QueueData queueData) {

        this.connectionFactory = connectionFactory;
        this.queueData = queueData;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;
    }

    @Bean
    public void createQueues() {

        queueData.getOperations().forEach(operation -> rabbitAdmin().declareQueue(createTopicByOperation(operation)));
    }

    private Queue createTopicByOperation(final String operation) {

        return new Queue(getTopicName(operation, queueData.getPrefix()));
    }

    private String getTopicName(final String operation, final String prefix) {

        return String.format(ConfigurationConstants.TOPICS_NAME_PATTERN, prefix, operation);
    }

}
