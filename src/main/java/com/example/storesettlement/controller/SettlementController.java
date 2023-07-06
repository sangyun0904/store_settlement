package com.example.storesettlement.controller;

import com.example.storesettlement.model.Settlement;
import com.example.storesettlement.services.SettlementService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @ApiResponse(responseCode = "200", description = "정산 리스트 조회", useReturnTypeSchema = true)
    @GetMapping
    public List<Settlement> settlementMain() {
        return settlementService.getSettlements();
    }
}
