package com.example.storesettlement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long settlement;
    @ManyToOne
    @NotNull
    private Owner owner;
    @NotNull
    private LocalDate settleDate;
    @NotNull
    private boolean isPaid;
}
