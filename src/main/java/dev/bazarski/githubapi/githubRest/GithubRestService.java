package dev.bazarski.githubapi.githubRest;

import dev.bazarski.githubapi.errors.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Service
public class GithubRestService {
    private final WebClient client;

    public GithubRestService(WebClient webClient) {
        this.client = webClient;
    }

    Flux<FullRepo> getAllReposNotForksFromUser(String name) {
        return getRepos(name)
                .filter(repo -> !repo.fork())
                .publishOn(Schedulers.fromExecutor(Executors.newVirtualThreadPerTaskExecutor()))
                .flatMapSequential(this::getFullRepo);
    }

    Mono<FullRepo> getFullRepo(Repo repo) {
        return getRepoBranches(repo.name(), repo.owner().login())
                .flatMap(branch -> Flux.just(new FullRepo.FlatBranch(
                        branch.name(),
                        branch.commit().sha()
                )))
                .collectList()
                .flatMap(branches -> Mono.just(new FullRepo(
                        repo.name(),
                        repo.owner().login(),
                        branches,
                        repo.fork()
                )));
    }

    Flux<Repo> getRepos(String name) {
        return client.get()
                .uri("users/{name}/repos", name)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    throw new UserNotFoundException(response.statusCode());
                })
                .bodyToFlux(Repo.class);
    }

    Flux<Branch> getRepoBranches(String repoName, String ownerLogin) {
        return this.client.get()
                .uri(STR."repos/\{ownerLogin}/\{repoName}/branches")
                .retrieve()
                .bodyToFlux(Branch.class);
    }
}
