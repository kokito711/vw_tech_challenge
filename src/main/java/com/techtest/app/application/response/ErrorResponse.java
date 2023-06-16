package com.techtest.app.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {

    @JsonProperty(value = "errorMessage")
    private final String message;
}
