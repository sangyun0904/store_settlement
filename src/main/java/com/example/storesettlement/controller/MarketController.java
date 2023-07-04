package com.example.storesettlement.controller;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.dto.MarketEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.services.MarketService;
import com.example.storesettlement.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping
    public DefaultResponse<List<Market>> marketList() {
        return DefaultResponse.res(200, "OK", marketService.getAllMarket());
    }

    @GetMapping("/{name}")
    public DefaultResponse<Market> marketDetail(@PathVariable(value = "name") String name) {
        return DefaultResponse.res(200, "OK", marketService.getMarket(name));
    }

    @PostMapping
    public DefaultResponse<Market> marketCreate(@RequestBody MarketCreateDto marketCreate) {
        return DefaultResponse.res(201, "Created", marketService.addMarket(marketCreate));
    }

    @PatchMapping("/{id}")
    public DefaultResponse<Market> marketUpdate(@PathVariable(value = "id") Long id, @RequestBody MarketEditDto marketEditDto) {
        return DefaultResponse.res(200, "OK", marketService.editMarket(id, marketEditDto));
    }

    @DeleteMapping("/{name}")
    public void marketDelete(@PathVariable(value = "name") String name) {
        marketService.deleteMarket(name);
    }

}
