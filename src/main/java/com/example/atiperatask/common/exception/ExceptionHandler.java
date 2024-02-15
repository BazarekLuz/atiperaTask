package com.example.atiperatask.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({
            ExternalApiException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageDTO handleExceptions(ExternalApiException e) {
        return new ErrorMessageDTO(e.getStatus(), e.getMessage());
    }
}
