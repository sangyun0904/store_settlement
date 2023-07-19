package com.example.storesettlement.controller;

import com.example.storesettlement.dto.SettlementCreateDto;
import com.example.storesettlement.model.Settlement;
import com.example.storesettlement.services.MarketService;
import com.example.storesettlement.services.SettlementService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlement")
@RequiredArgsConstructor
public class SettlementController {

    private final SettlementService settlementService;
    private final MarketService marketService;

    @ApiResponse(responseCode = "200", description = "정산 리스트 조회", useReturnTypeSchema = true)
    @GetMapping("/list")
    public List<Settlement> settlementMain() {
        return settlementService.getSettlements();
    }

    @ApiResponse(responseCode = "200", description = "정산 생성 및 수정", useReturnTypeSchema = true)
    @PostMapping()
    public DefaultResponse<Settlement> settlementMain(@RequestBody SettlementCreateDto settleDto) {
        Settlement settlement = settlementService.createSettlement(marketService.getMarketByName(settleDto.marketName()), settleDto.year(), settleDto.month());
        return DefaultResponse.res(200, "OK", settlement);
    }
}
