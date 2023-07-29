package com.example.storesettlement.services;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.dto.MarketEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.repositories.MarketRepository;
import com.example.storesettlement.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Market addMarket(MarketCreateDto marketCreate) {


        if (marketRepository.findByName(marketCreate.name()).isPresent()) {
            throw new IllegalStateException("같은 이름의 마켓이 존재합니다.");
        }
        Owner owner = ownerRepository.findByName(marketCreate.ownerName()).orElse(null);
        if (owner == null) {
            throw new IllegalStateException("해당 이름의 점주가 존재하지 않습니다.");
        }
        if (marketRepository.findByOwnerId(owner.getId()).isPresent()) {
            throw new IllegalStateException("이 점주는 이미 마켓이 존재합니다.");
        }

        Market market = Market.builder()
                .name(marketCreate.name())
                .ownerId(owner.getId())
                .address(marketCreate.address())
                .phone(marketCreate.phone())
                .settleDate(marketCreate.settleDate())
                .openDate(marketCreate.openDate())
                .uploadDate(LocalDate.now())
                .build();

        return marketRepository.save(market);
    }

    @Transactional
    public Market editMarket(Long id, MarketEditDto marketEdit) {
        Market market = marketRepository.findById(id).orElseThrow();

        Market updatedMarket = Market.builder()
                .id(market.getId())
                .address(marketEdit.address())
                .phone(marketEdit.phone())
                .name(marketEdit.name())
                .ownerId(market.getOwnerId())
                .openDate(market.getOpenDate())
                .uploadDate(market.getUploadDate())
                .settleDate(marketEdit.settleDate())
                .build();

        return marketRepository.save(updatedMarket);
    }

    @Transactional
    public void deleteMarket(Long id) {
        Market market = marketRepository.findById(id).orElseThrow();
        marketRepository.delete(market);
    }

    @Transactional
    public List<Market> getAllMarket() {
        return marketRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public Market getMarketById(Long marketId) {
        return marketRepository.findById(marketId).orElse(null);
    }

    @Transactional
    public Market getMarketByName(String name) {
        return marketRepository.findByName(name).orElse(null);
    }

}
