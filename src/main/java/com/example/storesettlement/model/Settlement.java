package com.example.storesettlement.model;

import jakarta.persistence.*;
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

    private long settlement;
    @ManyToOne
    private Owner owner;
    private LocalDate settleDate;
    private boolean isPaid;
}
