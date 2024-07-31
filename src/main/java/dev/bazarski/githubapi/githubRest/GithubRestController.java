package dev.bazarski.githubapi.githubRest;

import dev.bazarski.githubapi.errors.exceptions.WrongRequestHeaderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/github")
public class GithubRestController {
    private final GithubRestService githubRestService;

    public GithubRestController(GithubRestService service) {
        this.githubRestService = service;
    }

    @GetMapping("/users/{name}")
    private ResponseEntity<Flux<FullRepo>> findAllReposNotForks(
            @RequestHeader(value = HttpHeaders.ACCEPT) String acceptHeader,
            @PathVariable String name
    ) {
        if (!acceptHeader.equals("application/json"))
            throw new WrongRequestHeaderException(HttpStatus.BAD_REQUEST, "Wrong request headers");
        return ResponseEntity.ok(this.githubRestService.getAllReposNotForksFromUser(name));
    }
}
