package dev.bazarski.githubapi.errors.exceptions;

import org.springframework.http.HttpStatusCode;

public class WrongRequestHeaderException extends RuntimeException {
    private final String statusCode;
    private final String message;

    public WrongRequestHeaderException(HttpStatusCode statusCode, String message1) {
        super();
        this.statusCode = statusCode.toString();
        this.message = message1;
    }

    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
