package dev.bazarski.githubapi.githubRest;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

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
    void wireMockTest() {
        client.get()
                .uri("/api/github/users/BazarekLuz")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk();
    }
}
