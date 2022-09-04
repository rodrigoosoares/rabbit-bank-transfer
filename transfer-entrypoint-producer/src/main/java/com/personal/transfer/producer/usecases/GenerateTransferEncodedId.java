package com.personal.transfer.producer.usecases;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class GenerateTransferEncodedId {

    public GenerateTransferEncodedId() {

    }

    public String encodeId(final String sourceId, final String destinationId, final String amount, final String timestamp) {

        final String pureId = String.format("%s:%s:%s:%s", sourceId, destinationId, amount, timestamp);

        return Base64.getEncoder().encodeToString(pureId.getBytes());
    }

}
