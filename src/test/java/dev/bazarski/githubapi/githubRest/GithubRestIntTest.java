package dev.bazarski.githubapi.githubRest;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.hasItem;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 9000)
public class GithubRestIntTest {

    WebTestClient client;

    @BeforeEach
    void setup(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context)
                .build();
    }

    @Test
    void shouldReturnStatusOk() {
        client.get()
                .uri("/api/github/users/BazarekLuz")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldReturnStatusBadRequest_LackingHeader() {
        client.get()
                .uri("/api/github/users/BazarekLuz")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void shouldReturnEmptyArrayBody() {
        client.get()
                .uri("/api/github/users/bazarski")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[]");
    }

    @Test
    void shouldReturnStatusNotFound_GivenWrongName() {
        client.get()
                .uri("/api/github/users/bsf fad ef")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldReturnErrorMessage_GivenWrongName() {
        client.get()
                .uri("/api/github/users/bsf fad ef")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectBody()
                .jsonPath("$.statusCode").exists()
                .jsonPath("$.message").exists();
    }

    @Test
    void shouldReturnExactlyFourRepos() {
        client.get()
                .uri("/api/github/users/BazarekLuz")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectBody().jsonPath("$.size()").isEqualTo(4);
    }

    @Test
    void everyRepoShouldHaveOneBranch() {
        client.get()
                .uri("/api/github/users/BazarekLuz")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectBody()
                .jsonPath("$[*].branches.length()").value(hasItem(1));
    }

    @Test
    void shouldReturnRepoWithTwoBranches() {
        client.get()
                .uri("/api/github/users/ArturChmura")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectBody()
                .jsonPath("$[*].branches.length()").value(hasItem(2));
    }

    @Test
    void responseShouldNotContainForkAttribute() {
        client.get()
                .uri("/api/github/users/ArturChmura")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectBody()
                .jsonPath("$[*].fork").doesNotExist();
    }
}
