package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;

public record OwnerCreateDto(
        @NotBlank String name,
        @NotBlank String marketName,
        @NotBlank String accountNum,
        @NotBlank String username
) {
}
