package dev.bazarski.githubapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(GithubRestProperties props) {
        return WebClient.builder()
                .uriBuilderFactory(new DefaultUriBuilderFactory(props.getApiUrl()))
                .defaultHeader(HttpHeaders.AUTHORIZATION, STR."Bearer \{props.getApiToken()}")
                .defaultHeader(HttpHeaders.USER_AGENT, props.getUsername())
                .build();
    }
}
