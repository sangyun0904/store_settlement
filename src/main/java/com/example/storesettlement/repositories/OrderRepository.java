package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    Optional<OrderInfo> findByOrderNum(Long orderNum);

    List<OrderInfo> findAllByOrderByOrderDateDesc();
}
