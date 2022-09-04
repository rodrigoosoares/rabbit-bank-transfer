package com.personal.transfer.producer.models.entrypoint;


import com.personal.transfer.producer.models.domain.TransactionType;
import com.personal.transfer.producer.validators.annotations.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest {

    @NotNull
    private String sourceId;

    @NotNull
    private String destinationId;

    @Min(1)
    private Long amount;

    @NotNull
    private Instant timestamp;

    @NotNull
    @ValidEnum(TransactionType.class)
    private String type;
}
