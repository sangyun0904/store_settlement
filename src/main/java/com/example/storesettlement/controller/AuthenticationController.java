package com.example.storesettlement.controller;

import com.example.storesettlement.dto.AuthenticationRequest;
import com.example.storesettlement.dto.AuthenticationResponse;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.services.AuthenticationService;
import com.example.storesettlement.utils.DefaultResponse;
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

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ) {
        return  new ResponseEntity(DefaultResponse.res(200, "회원가입 성공", authenticationService.register(request)), HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return  new ResponseEntity(DefaultResponse.res(200, "로그인 성공", authenticationService.authenticate(request)), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

}
