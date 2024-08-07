package dev.bazarski.githubapi.githubRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GithubRestServiceTest {

    @Autowired
    GithubRestService service;

    String user = "bazarekluz";

//    @Test
//    void shouldGetReposAllPublicReposOfUser() {
//        List<Repo> repos = service.getRepos(user);
//        assertFalse(repos.isEmpty());
//    }
//
//    @Test
//    void shouldGetBranches() {
//        String repoName = "atiperaTask";
//        List<Branch> branches = service.getRepoBranches(repoName, user);
//        assertFalse(branches.isEmpty());
//    }
//
//    @Test
//    void shouldReturnNotForks() {
//        List<FullRepo> repos = service.getAllReposNotForksFromUser("arturchmura");
//        repos.forEach(fullRepo -> assertFalse(fullRepo.fork()));
//    }
//
//    @Test
//    void shouldGetExactBranch() {
//        String repoName = "binksake-core";
//        Branch branch = new Branch("master", new Branch.Commit("cb93725c2c3d62860c1150637de88ea17a945fc2"));
//        List<Branch> branches = service.getRepoBranches(repoName, user);
//
//        assertEquals(branches.getFirst(), branch);
//    }
//
//    @Test
//    void shouldThrowExceptionGivenWrongUsername() {
//        String user = "sdfsddregedrr fwef gs";
//        assertThrows(UserNotFoundException.class, () -> service.getAllReposNotForksFromUser(user));
//    }
}