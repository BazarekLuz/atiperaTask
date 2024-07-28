package dev.bazarski.githubapi.githubRest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record FullRepo(
        String name,
        String ownerLogin,
        List<FlatBranch> branches,
        @JsonIgnore
        boolean fork
) {
    record FlatBranch(
            String name,
            String sha
    ) {}
}
