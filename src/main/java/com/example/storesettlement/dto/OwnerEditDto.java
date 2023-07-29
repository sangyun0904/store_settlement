package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;

public record OwnerEditDto(
        @NotBlank String name,
        @NotBlank String accountNum
) {
}
