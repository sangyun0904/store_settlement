package com.example.storesettlement.controller;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.dto.MarketEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.services.MarketService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @ApiResponse(responseCode = "200", description = "마켓 리스트 조회", useReturnTypeSchema = true)
    @GetMapping
    public DefaultResponse<List<Market>> marketList() {
        return DefaultResponse.res(200, "OK", marketService.getAllMarket());
    }

    @ApiResponse(responseCode = "200", description = "마켓 상세 조회", useReturnTypeSchema = true)
    @GetMapping("/{name}")
    public DefaultResponse<Market> marketDetail(@PathVariable(value = "name") @Parameter(name = "name", description = "마켓 명") String name ) {
        return DefaultResponse.res(200, "OK", marketService.getMarket(name));
    }

    @ApiResponse(responseCode = "200", description = "마켓 추가", useReturnTypeSchema = true)
    @PostMapping
    public DefaultResponse<Market> marketCreate(@RequestBody MarketCreateDto marketCreate) {
        return DefaultResponse.res(200, "OK", marketService.addMarket(marketCreate));
    }

    @ApiResponse(responseCode = "200", description = "마켓 수정", useReturnTypeSchema = true)
    @PatchMapping("/{name}")
    public DefaultResponse<Market> marketUpdate(@PathVariable(value = "id") @Parameter(name = "name", description = "마켓 명") String name , @RequestBody MarketEditDto marketEditDto) {
        return DefaultResponse.res(200, "OK", marketService.editMarket(name, marketEditDto));
    }

    @ApiResponse(responseCode = "200", description = "마켓 삭제", useReturnTypeSchema = true)
    @DeleteMapping("/{name}")
    public DefaultResponse marketDelete(@PathVariable(value = "name") @Parameter(name = "name", description = "마켓 명") String name) {
        marketService.deleteMarket(name);
        return DefaultResponse.res(200, "OK");
    }

}
