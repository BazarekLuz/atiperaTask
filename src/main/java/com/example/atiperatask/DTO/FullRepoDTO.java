package com.example.atiperatask.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullRepoDTO {
    private String repositoryName;
    private String ownerLogin;
    private List<FullBranchDTO> branches;
}
