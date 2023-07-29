package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderCreateDto(
        @NotBlank String product,
        @NotNull Long price,
        @NotBlank String customer,
        @NotBlank String marketName

) {
}
