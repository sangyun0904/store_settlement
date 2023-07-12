package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Integer> {
}
