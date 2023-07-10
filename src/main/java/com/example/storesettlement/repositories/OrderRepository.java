package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.OrderInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    Optional<OrderInfo> findByOrderNum(Long orderNum);

    List<OrderInfo> findAllByOrderByOrderDateDesc();

    @Query(value = "SELECT SUM(price) FROM order_info WHERE order_date > ?1 AND order_date < ?2 and order_state = COMPLETED", nativeQuery = true)
    Long findAllOrderForSettlement(LocalDate start, LocalDate end);

    List<OrderInfo> getAllByMarket(Market market);
}
