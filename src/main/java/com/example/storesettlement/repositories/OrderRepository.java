package com.example.storesettlement.repositories;

import com.example.storesettlement.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
}
