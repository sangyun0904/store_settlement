package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SettlementCreateDto(
        @NotBlank String marketName,
        @NotBlank String year,
        @NotBlank String month
) {
}
