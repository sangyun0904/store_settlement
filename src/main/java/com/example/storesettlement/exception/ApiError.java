package com.example.storesettlement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class ApiError {
    private HttpStatus status;
    private int customCode;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, int customCode, String message, String error) {
        this.status = status;
        this.customCode = customCode;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
