package com.example.atiperatask.service;

import com.example.atiperatask.DTO.BranchDTO;
import com.example.atiperatask.DTO.FullBranchDTO;
import com.example.atiperatask.DTO.FullRepoDTO;
import com.example.atiperatask.DTO.RepoDTO;
import com.example.atiperatask.common.AutoMapper;
import com.example.atiperatask.common.exception.ExternalApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Value("${github.api-url}")
    private String githubApiUrl;
    @Value("${github.api-token}")
    private String token;
    private final AutoMapper autoMapper;
    public List<FullRepoDTO> getUserRepos(String name, String header) {
        try {
            System.out.println(header);
            WebClient webClient = WebClient.create(githubApiUrl);
            List<RepoDTO> repos = webClient.get()
                    .uri("/users/{name}/repos", name)
                    .header(header)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToFlux(RepoDTO.class)
                    .collectList()
                    .block();

            ArrayList<FullRepoDTO> customResponse = new ArrayList<>();
            if (repos != null) {
                repos.forEach(repoDTO -> {
                    if (!repoDTO.getFork()) {
                        List<BranchDTO> branches = getRepoBranches(repoDTO.getOwner().getLogin(), repoDTO.getName());
                        List<FullBranchDTO> finalBranches = branches.stream()
                                .map(autoMapper::mapToFullBranchDTO)
                                .toList();
                        FullRepoDTO fullRepoDTO = FullRepoDTO.builder()
                                .repositoryName(repoDTO.getName())
                                .ownerLogin(repoDTO.getOwner().getLogin())
                                .branches(finalBranches)
                                .build();
                        customResponse.add(fullRepoDTO);
                    }
                });
            }
            return customResponse;
        } catch (WebClientResponseException e) {
            throw new ExternalApiException(e.getStatusCode().value(), e.getMessage());
        }
    }

    public List<BranchDTO> getRepoBranches(String ownerLogin, String repoName) {
        WebClient webClient = WebClient.create(githubApiUrl);
        return webClient.get()
                .uri("/repos/{ownerLogin}/{repoName}/branches", ownerLogin, repoName)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToFlux(BranchDTO.class)
                .collectList()
                .block();
    }
}
