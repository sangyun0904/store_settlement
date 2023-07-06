package com.example.storesettlement.dto;

import com.example.storesettlement.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberCreateDto(
        @NotBlank String username,
        @NotBlank String password,
        @Email String email,
        @NotNull String role
        ) {
}
