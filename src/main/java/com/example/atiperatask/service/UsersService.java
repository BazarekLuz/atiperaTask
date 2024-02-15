package com.example.atiperatask.service;

import com.example.atiperatask.DTO.RepoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Value("${github.api-url}")
    private String githubApiUrl;
    public List<RepoDTO> getUserRepos(String name, String header) {
        WebClient webClient = WebClient.create(githubApiUrl);
        List<RepoDTO> repoDTO = webClient.get()
                .uri("/users/{name}/repos", name)
                .header(header)
                .retrieve()
                .bodyToFlux(RepoDTO.class)
                .collectList()
                .block();
        if (repoDTO != null)
            repoDTO.forEach(System.out::println);
        return new ArrayList<>();
    }
}
