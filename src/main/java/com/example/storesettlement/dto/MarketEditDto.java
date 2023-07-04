package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MarketEditDto(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @NotNull
        int settleDate
) {
}
