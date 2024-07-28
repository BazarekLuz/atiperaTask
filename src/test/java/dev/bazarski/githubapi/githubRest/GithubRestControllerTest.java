package dev.bazarski.githubapi.githubRest;

import dev.bazarski.githubapi.errors.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MissingRequestHeaderException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class GithubRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    GithubRestService service;

    String user = "bazarekluz";

    @Test
    void shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(STR."/api/github/users/\{user}").accept("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionLackingHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(STR."/api/github/users/\{user}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MissingRequestHeaderException.class, result.getResolvedException()));
    }

    // exactly 4 repos, because my account has 4 public repos, it may change in the future
    @Test
    void shouldFindExactlyFourRepos() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(STR."/api/github/users/\{user}").accept("application/json"))
                .andExpect(jsonPath("$.size()", is(4)));
    }

    @Test
    void shouldThrowExceptionGivenWrongUsername() throws Exception {
        String user = "sgdrgr";
        mvc.perform(MockMvcRequestBuilders.get(STR."/api/github/users/\{user}").accept("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));
    }
}