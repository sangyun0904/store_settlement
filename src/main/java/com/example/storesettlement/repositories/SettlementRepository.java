package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {
    @Query(value = "SELECT * FROM settlement WHERE settle_date = ?", nativeQuery = true)
    List<Settlement> findAllBySettleDate(LocalDate settleDate);

    @Query(value = "SELECT * FROM settlement WHERE Market = ?1 and yearNum = ?2 and monthNum = ?3", nativeQuery = true)
    Optional<Settlement> findByMarketAndYearAndMonth(Market market, int year, int month);
}
