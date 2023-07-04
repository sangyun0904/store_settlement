package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market, Long> {

    List<Market> findAllByOrderByNameAsc();

    Optional<Market> findByName(String name);
}
