package com.personal.transfer.producer.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.personal.transfer.producer.models.domain.Transaction;
import com.personal.transfer.producer.models.domain.TransactionType;
import com.personal.transfer.producer.models.entrypoint.TransactionRequest;

@Component
public class TransactionRequestToTransactionConverter implements Converter<TransactionRequest, Transaction> {

    @Override
    public Transaction convert(final TransactionRequest transactionRequest) {

        return Transaction.builder()
                .sourceId(transactionRequest.getSourceId())
                .destinationId(transactionRequest.getDestinationId())
                .amount(transactionRequest.getAmount())
                .timestamp(transactionRequest.getTimestamp().toEpochMilli())
                .type(TransactionType.valueOf(transactionRequest.getType()))
                .build();

    }
}
