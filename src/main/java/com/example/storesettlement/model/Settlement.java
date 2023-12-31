package com.example.storesettlement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long settlement;
    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Owner owner;
    @NotNull
    private int yearNum;
    @NotNull
    private int monthNum;
    @NotNull
    private LocalDate settleDate;
    @NotNull
    private boolean isPaid;
}
