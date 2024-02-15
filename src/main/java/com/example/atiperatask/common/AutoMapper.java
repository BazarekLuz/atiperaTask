package com.example.atiperatask.common;

import com.example.atiperatask.DTO.BranchDTO;
import com.example.atiperatask.DTO.FullBranchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoMapper {
    public FullBranchDTO mapToFullBranchDTO(BranchDTO branchDTO) {
        return FullBranchDTO.builder()
                .name(branchDTO.getName())
                .lastCommitSha(branchDTO.getCommit().getSha())
                .build();
    }
}
