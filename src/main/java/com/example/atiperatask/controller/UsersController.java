package com.example.atiperatask.controller;

import com.example.atiperatask.DTO.FullRepoDTO;
import com.example.atiperatask.request.UserRequest;
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
    @GetMapping(value = "/repos")
    private ResponseEntity<List<FullRepoDTO>> findAllUserRepos(@RequestBody UserRequest userRequest,
                                                               @RequestHeader("Accept") String header) {
        return ResponseEntity.ok(usersService.getUserRepos(userRequest.getName(), header));
    }
}
