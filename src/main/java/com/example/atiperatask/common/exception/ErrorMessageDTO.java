package com.example.atiperatask.common.exception;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDTO {
    private Integer status;
    private String message;
}
