package com.personal.transfer.consumer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    private String sourceId;
    private String destinationId;
    private Long amount;
    private Instant timestamp;
    private String type;
}
