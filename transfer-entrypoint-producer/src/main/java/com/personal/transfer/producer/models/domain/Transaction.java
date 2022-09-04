package com.personal.transfer.producer.models.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transaction {

    private String sourceId;
    private String destinationId;
    private Long amount;
    private Instant timestamp;
    private TransactionType type;
}
