package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
