package dev.bazarski.githubapi.errors;

public record ErrorMessage(
        String statusCode,
        String message
) { }
