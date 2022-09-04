package com.personal.transfer.producer.controllers;

import com.personal.transfer.producer.converters.TransactionRequestToTransactionConverter;
import com.personal.transfer.producer.models.domain.RequestHeaders;
import com.personal.transfer.producer.models.domain.Transaction;
import com.personal.transfer.producer.models.entrypoint.TransactionRequest;
import com.personal.transfer.producer.usecases.ProcessTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private final TransactionRequestToTransactionConverter transactionRequestToTransactionConverter;
    private final ProcessTransaction processTransaction;

    @Autowired
    public TransactionController(final TransactionRequestToTransactionConverter transactionRequestToTransactionConverter,
                                 final ProcessTransaction processTransaction) {
        this.transactionRequestToTransactionConverter = transactionRequestToTransactionConverter;
        this.processTransaction = processTransaction;
    }

    @PostMapping
    public ResponseEntity<?> responseEntity(@RequestHeader final String traceId,
                                            @RequestBody @Valid final TransactionRequest transactionRequest) {

        log.info("Start processing transaction for request trace id: {}", traceId);

        final Transaction transaction = transactionRequestToTransactionConverter.convert(transactionRequest);

        final RequestHeaders requestHeaders = RequestHeaders.builder()
                .headers(createRequestHeaders(traceId))
                .build();

        processTransaction.execute(requestHeaders, transaction);

        log.info("Finished processing transaction for request trace id: {}", traceId);

        return ResponseEntity.accepted().build();
    }

    private Map<String, String> createRequestHeaders(final String traceId) {

        return Map.of(
                "traceId", traceId
        );
    }

}
