package dev.bazarski.githubapi.githubRest;

import java.util.List;

public record FullRepo(
        String name,
        String ownerLogin,
        List<FlatBranch> branches
) {
    record FlatBranch(
            String name,
            String sha
    ) {}
}
