package com.example.storesettlement.controller;

import com.example.storesettlement.model.Settlement;
import com.example.storesettlement.services.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/settlement")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;

    @GetMapping
    public List<Settlement> settlementMain() {
        return settlementService.getSettlements();
    }
}
