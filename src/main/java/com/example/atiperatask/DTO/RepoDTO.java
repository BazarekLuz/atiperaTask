package com.example.atiperatask.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepoDTO {
    private String name;
    private OwnerDTO owner;
    private Boolean fork;
}
