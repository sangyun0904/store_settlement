package com.example.storesettlement.services;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.dto.MarketEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.repositories.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    public Market addMarket(MarketCreateDto marketCreate) {

        Market market = Market.builder()
                .name(marketCreate.name())
                .address(marketCreate.address())
                .phone(marketCreate.phone())
                .settleDate(marketCreate.settleDate())
                .openDate(marketCreate.openDate())
                .uploadDate(LocalDate.now())
                .build();

        return marketRepository.save(market);
    }

    public Market editMarket(Long id, MarketEditDto marketEdit) {
        Market market = marketRepository.findById(id).orElseThrow();

        Market updatedMarket = Market.builder()
                .id(market.getId())
                .address(marketEdit.address())
                .phone(marketEdit.phone())
                .name(marketEdit.name())
                .settleDate(marketEdit.settleDate())
                .build();

        return marketRepository.save(updatedMarket);
    }

    public void deleteMarket(String name) {
        Market market = marketRepository.findByName(name).orElseThrow();
        marketRepository.delete(market);
    }

    public List<Market> getAllMarket() {
        return marketRepository.findAllByOrderByNameAsc();
    }

    public Market getMarket(String name) {
        return marketRepository.findByName(name).orElseThrow();
    }

}