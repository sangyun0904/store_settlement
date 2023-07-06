package com.example.storesettlement.services;

import com.example.storesettlement.model.Settlement;
import com.example.storesettlement.repositories.OrderRepository;
import com.example.storesettlement.repositories.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final OrderRepository orderRepository;

    public List<Settlement> getSettlements() {
        return settlementRepository.findAll();
    }
}
