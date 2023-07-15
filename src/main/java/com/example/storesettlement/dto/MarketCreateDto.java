package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MarketCreateDto(
        @NotBlank String name,
        @NotBlank String ownerName,
        @NotBlank String address,
        @NotBlank String phone,
        @NotNull int settleDate,
        @NotNull LocalDate openDate
) {
}
