package com.example.storesettlement.services;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.model.Market;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    void 마켓생성() {
        MarketCreateDto dto = new MarketCreateDto("market","owner", "잠실로 88", "01000000000", 1, LocalDate.now());
        Market market = marketService.addMarket(dto);
        assertThat(market.getName()).isEqualTo("market");
    }
}