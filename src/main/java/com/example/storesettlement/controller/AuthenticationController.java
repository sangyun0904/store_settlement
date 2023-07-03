package com.example.storesettlement.controller;

import com.example.storesettlement.dto.AuthenticationRequest;
import com.example.storesettlement.dto.AuthenticationResponse;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.services.AuthenticationService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @ApiResponse(responseCode = "200", description = "회원가입 성공", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = DefaultResponse.class)) })
    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity(DefaultResponse.res(200, "회원가입 성공", authenticationService.register(request)), HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "로그인 성공", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = DefaultResponse.class)) })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return new ResponseEntity(DefaultResponse.res(200, "로그인 성공", authenticationService.authenticate(request)), HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "토큰 리프레시 성공", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = DefaultResponse.class)) })
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return new ResponseEntity(DefaultResponse.res(200, "토큰 리프레시 성공", authenticationService.refreshToken(request, response)), HttpStatus.OK);
    }

}
