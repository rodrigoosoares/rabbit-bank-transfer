package com.personal.transfer.producer.models.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class RequestHeaders {

    private Map<String, String> headers;
}
