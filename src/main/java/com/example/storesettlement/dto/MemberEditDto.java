package com.example.storesettlement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberEditDto(
        @Email String email
) {
}
