package com.example.storesettlement.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

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
