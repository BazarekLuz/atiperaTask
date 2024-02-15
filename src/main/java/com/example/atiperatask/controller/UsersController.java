package com.example.atiperatask.controller;

import com.example.atiperatask.DTO.RepoDTO;
import com.example.atiperatask.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;
    @GetMapping("/repos")
    private ResponseEntity<List<RepoDTO>> findAllUserRepos(@RequestParam String name,
                                                           @RequestHeader("Accept") String accept) {
        return ResponseEntity.ok(usersService.getUserRepos(name, accept));
    }
}
