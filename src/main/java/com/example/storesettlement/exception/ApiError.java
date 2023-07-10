package com.example.storesettlement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private int customCode;
    private String message;
    private String errors;

}
