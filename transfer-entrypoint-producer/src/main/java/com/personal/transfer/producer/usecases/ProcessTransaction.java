package com.personal.transfer.producer.usecases;

import com.personal.transfer.producer.models.domain.RequestHeaders;
import com.personal.transfer.producer.models.domain.Transaction;
import com.personal.transfer.producer.models.domain.TransactionMessage;
import com.personal.transfer.producer.producers.TransactionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTransaction {

    private final TransactionProducer transactionProducer;
    private final GenerateTransferEncodedId generateTransferEncodedId;

    @Autowired
    public ProcessTransaction(TransactionProducer transactionProducer,
                              GenerateTransferEncodedId generateTransferEncodedId) {

        this.transactionProducer = transactionProducer;
        this.generateTransferEncodedId = generateTransferEncodedId;
    }

    public void execute(RequestHeaders requestHeaders, Transaction transaction) {

        TransactionMessage transactionMessage = createTransactionMessage(transaction);

        transactionProducer.produce(requestHeaders, transactionMessage);
    }

    private TransactionMessage createTransactionMessage(Transaction transaction) {

        return TransactionMessage.builder()
                .key(generateTransactionKey(transaction))
                .value(transaction)
                .build();
    }

    private String generateTransactionKey(Transaction transaction) {

        return generateTransferEncodedId.encodeId(transaction.getSourceId(),
                transaction.getDestinationId(),
                transaction.getAmount().toString(),
                transaction.getTimestamp().toString());
    }

}
