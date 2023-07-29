package com.example.storesettlement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private int statusCode;
    private String responseMessage;
    private String errors;

}
