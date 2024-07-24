package dev.bazarski.githubapi.errors.exceptions;

import org.springframework.http.HttpStatusCode;

public class UserNotFoundException extends RuntimeException {
    private final String statusCode;
    private final String message;

    public UserNotFoundException(HttpStatusCode statusCode, String message) {
        super();
        this.statusCode = statusCode.toString();
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
