package dev.bazarski.githubapi.errors;

import dev.bazarski.githubapi.errors.exceptions.UserNotFoundException;
import dev.bazarski.githubapi.errors.exceptions.WrongRequestHeaderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = {
            UserNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleExceptions(UserNotFoundException exception) {
        return new ErrorMessage(exception.getStatusCode(), exception.getMessage());
    }

    @ExceptionHandler(value = {
            WrongRequestHeaderException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleExceptions(WrongRequestHeaderException exception) {
        return new ErrorMessage(exception.getStatusCode(), exception.getMessage());
    }

//    @ExceptionHandler(value = {
//            MissingRequestHeaderException.class,
//    })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorMessage handleExceptions(Exception exception) {
//        return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
//    }
}
