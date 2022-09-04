package com.personal.transfer.producer.producers;

import com.personal.transfer.producer.models.domain.RequestHeaders;
import com.personal.transfer.producer.models.domain.TransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {


    @Autowired
    public TransactionProducer() {

    }

    public void produce(final RequestHeaders requestHeaders, final TransactionMessage transactionMessage) {


    }

}
