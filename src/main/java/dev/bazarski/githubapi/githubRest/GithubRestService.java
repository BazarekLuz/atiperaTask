package dev.bazarski.githubapi.githubRest;

import dev.bazarski.githubapi.config.GithubRestProperties;
import dev.bazarski.githubapi.errors.exceptions.UserNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class GithubRestService {
    private final RestClient restClient;

    public GithubRestService(RestClient.Builder builder, GithubRestProperties props) {
        this.restClient = builder
                .uriBuilderFactory(new DefaultUriBuilderFactory(props.getApiUrl()))
                .defaultHeader("Authorization", STR."Bearer\{props.getApiToken()}")
                .build();
    }

//    List<FullRepo> getAllReposNotForksFromUser(String name) {
//        return getRepos(name)
//                .stream()
//                .filter(repo -> !repo.fork())
//                .map(repo -> new FullRepo(
//                        repo.name(),
//                        repo.owner().login(),
//                        getRepoBranches(repo.name(), repo.owner().login())
//                                .stream()
//                                .map(branch -> new FullRepo.FlatBranch(
//                                        branch.name(),
//                                        branch.commit().sha()
//                                )).toList())
//                ).toList();
//    }


//    List<FullRepo> getAllReposNotForksFromUser(String name) {
//        List<Repo> repos = getRepos(name)
//                .stream()
//                .filter(repo -> !repo.fork())
//                .toList();
//
//        List<FullRepo> fullFinalRepos;
//        List<Future<FullRepo>> fullRepos = new ArrayList<>();
//
//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            for (var repo : repos) {
//                Future<FullRepo> futureRepo =  executor.submit(() -> getFullRepo(repo));
//                fullRepos.add(futureRepo);
//            }
//        }
//        fullFinalRepos = fullRepos.stream().map(futureRepo -> {
//            try {
//                return futureRepo.get();
//            } catch (InterruptedException | ExecutionException ex) {
//                System.out.println("XDD");
//            }
//            return null;
//        }).toList();
//
//        return fullFinalRepos;
//    }

    List<FullRepo> getAllReposNotForksFromUser(String name) {
        List<Repo> repos = getRepos(name)
                .stream()
                .filter(repo -> !repo.fork())
                .toList();

        List<Future<FullRepo>> futureFullRepos = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (var repo : repos) {
                Future<FullRepo> futureRepo =  executor.submit(() -> getFullRepo(repo));
                futureFullRepos.add(futureRepo);
            }
        }

        return futureFullRepos
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

private FullRepo getFullRepo(Repo repo)  {
    List<FullRepo.FlatBranch> branches = getRepoBranches(repo.name(), repo.owner().login())
            .stream()
            .map(branch -> new FullRepo.FlatBranch(
                    branch.name(),
                    branch.commit().sha()
            )).toList();
    return new FullRepo(repo.name(), repo.owner().login(), branches);
}

//    List<FullRepo> getAllReposNotForksFromUser(String name) {
//    return getRepos(name)
//            .stream()
//            .filter(repo -> !repo.fork())
//            .map(repo -> {
//                if (repo instanceof Repo(String repoName, Repo.Owner(String login), boolean _)) {
//                    return new FullRepo(repoName, login, getRepoBranches(repoName, login)
//                            .stream()
//                            .map(branch -> {
//                                if (branch instanceof Branch(String branchName, Branch.Commit(String sha))) {
//                                    return new FullRepo.FlatBranch(branchName, sha);
//                                }
//                                return new FullRepo.FlatBranch(null, null);
//                            }).toList());
//                }
//                return new FullRepo(null, null, null);
//            }).toList();
//    }

//    List<FullRepo> getAllReposNotForksFromUser(String name) {
//        return getRepos(name)
//                .stream()
//                .filter(repo -> !repo.fork())
//                .map(repo -> switch (repo) {
//                    case Repo(String repoName, Repo.Owner(String login), var _)
//                            -> new FullRepo(repoName, login, getRepoBranches(repoName, login)
//                            .stream()
//                            .map(branch -> switch (branch) {
//                                case Branch(String branchName, Branch.Commit(String sha))
//                                        -> new FullRepo.FlatBranch(branchName, sha);
//                            }).toList());
//                }).toList();
//    }

    private List<Repo> getRepos(String name) {
        return restClient.get()
                .uri("users/{name}/repos", name)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((_, response) -> {
                    throw new UserNotFoundException(response.getStatusCode(), response.getStatusText());
                }))
                .body(new ParameterizedTypeReference<>() {});
    }

    List<Branch> getRepoBranches(String repoName, String ownerLogin) {
        return this.restClient.get()
                .uri(STR."repos/\{ownerLogin}/\{repoName}/branches")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
