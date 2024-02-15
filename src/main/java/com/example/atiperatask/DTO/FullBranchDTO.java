package com.example.atiperatask.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullBranchDTO {
    private String name;
    private String lastCommitSha;
}
