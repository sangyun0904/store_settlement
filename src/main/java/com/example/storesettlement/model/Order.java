package com.example.storesettlement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNum;
    private String product;
    private int price;
    private String customer;
    @ManyToOne
    private Market market;
    private int serviceCharge;
    private LocalDate orderDate;
    private OrderState orderState;

    public static enum OrderState {
        ORDERED, CANCELED, COMPLETED
    }
}
