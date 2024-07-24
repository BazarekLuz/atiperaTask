package dev.bazarski.githubapi.githubRest;

public record Repo(
        String name,
        Owner owner,
        boolean fork
) {
    record Owner(String login) {}
}
