package com.example.storesettlement.services;

import com.example.storesettlement.dto.AuthenticationRequest;
import com.example.storesettlement.dto.AuthenticationResponse;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.repositories.MemberRepository;
import com.example.storesettlement.utils.DefaultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public Member register(RegisterRequest request) {
        validateDuplicateUser(request.getUsername());
        Member member = Member.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .createdDate(LocalDate.now())
                .build();
        return memberRepository.save(member);
    }

    @Transactional
    private void validateDuplicateUser(String username) {
        memberRepository.findByUsername(username)
                .ifPresent(m -> {
                    throw new IllegalStateException("Username '" + username + "' already exists.");
                });
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getUsername() + " " +  request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow();
        System.out.println(member);
        String jwtToken = jwtService.generateAccessToken(member);
        String refreshToken = jwtService.generateRefreshToken(member);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public AuthenticationResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            Member member = this.memberRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, member)) {
                String accessToken = jwtService.generateAccessToken(member);
                return AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }
        return null;
    }
}
