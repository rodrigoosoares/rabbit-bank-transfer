package com.personal.transfer.producer.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq.queues")
public class QueueData {

    private String prefix;
    private List<String> operations;

    public void setPrefix(final String prefix) {

        this.prefix = prefix;
    }

    public String getPrefix() {

        return prefix;
    }

    public void setOperations(final List<String> operations) {

        this.operations = operations;
    }

    public List<String> getOperations() {

        return operations;
    }
}
