package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
