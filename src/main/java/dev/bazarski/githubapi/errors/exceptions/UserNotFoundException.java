package dev.bazarski.githubapi.errors.exceptions;

import dev.bazarski.githubapi.errors.ErrorMessages;
import org.springframework.http.HttpStatusCode;

public class UserNotFoundException extends RuntimeException {
    private final String status;
    private final String message;

    public UserNotFoundException(HttpStatusCode statusCode) {
        super();
        this.status = statusCode.toString();
        this.message = ErrorMessages.USER_NOT_FOUND;
    }

    public String getStatusCode() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
