package com.personal.transfer.producer.producers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.transfer.producer.models.domain.RequestHeaders;
import com.personal.transfer.producer.models.domain.TransactionMessage;

@Service
public class TransactionProducer {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public TransactionProducer(final AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void produce(final RequestHeaders requestHeaders, final TransactionMessage transactionMessage) {

        amqpTemplate.convertAndSend("transactions-processor-transfer", transactionMessage);

    }

}
