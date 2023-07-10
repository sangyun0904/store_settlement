package com.example.storesettlement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotNull
    private int settleDate;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Owner owner;
    @NotNull
    private LocalDate openDate;
    @NotNull
    private LocalDate uploadDate;
}
