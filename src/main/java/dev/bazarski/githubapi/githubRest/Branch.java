package dev.bazarski.githubapi.githubRest;

public record Branch(
        String name,
        Commit commit
) {
    record Commit(String sha) {}
}
