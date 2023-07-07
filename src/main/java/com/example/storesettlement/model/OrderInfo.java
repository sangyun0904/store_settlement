package com.example.storesettlement.model;

import com.example.storesettlement.model.enums.OrderState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long orderNum;
    @NotBlank
    private String product;
    @NotNull
    private Long price;
    @NotBlank
    private String customer;
    @ManyToOne
    private Market market;
    @NotNull
    private Long serviceCharge;
    @NotNull
    private LocalDate orderDate;
    @NotNull
    private OrderState orderState;

}
