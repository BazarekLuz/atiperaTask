package com.example.atiperatask.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExternalApiException extends RuntimeException {
    private Integer status;
    private String message;
    public ExternalApiException(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
