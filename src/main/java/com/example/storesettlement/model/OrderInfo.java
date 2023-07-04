package com.example.storesettlement.model;

import com.example.storesettlement.model.enums.OrderState;
import jakarta.persistence.*;
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
    private Long orderNum;
    private String product;
    private Long price;
    private String customer;
    @ManyToOne
    private Market market;
    private Long serviceCharge;
    private LocalDate orderDate;
    private OrderState orderState;

}
