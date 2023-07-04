package com.example.storesettlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderCreateDto(
        @NotNull Long orderNum,
        @NotBlank String product,
        @NotNull Long price,
        @NotBlank String customer,
        @NotBlank String marketName

) {
}
