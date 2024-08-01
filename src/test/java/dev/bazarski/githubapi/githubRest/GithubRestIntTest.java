package dev.bazarski.githubapi.githubRest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubRestIntTest {

    @LocalServerPort
    int randomServerPort;

    WebTestClient client;

    @BeforeEach
    void setup(ApplicationContext context) {
        client = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void simpleTest() {

    }
}
