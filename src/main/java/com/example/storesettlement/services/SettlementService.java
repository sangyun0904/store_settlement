package com.example.storesettlement.services;

import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Settlement;
import com.example.storesettlement.repositories.OrderRepository;
import com.example.storesettlement.repositories.OwnerRepository;
import com.example.storesettlement.repositories.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final OrderRepository orderRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public Settlement createSettlement(Market market, String year, String month) {
        LocalDate settle_start_date = LocalDate.parse(year+"-"+month+"-"+market.getSettleDate());
        LocalDate settle_end_date = LocalDate.parse(year+"-"+month+"-"+market.getSettleDate());

        Long orderIncome = orderRepository.findAllOrderForSettlement(settle_start_date, settle_end_date);

        Settlement settlement = settlementRepository.findByMarketAndYearAndMonth(market, Integer.parseInt(year), Integer.parseInt(month)).orElse(null);

        if (settlement == null) {
            settlement = Settlement.builder()
                    .settlement(orderIncome)
                    .owner(ownerRepository.findById(market.getOwnerId()).orElseThrow())
                    .settleDate(LocalDate.now())
                    .isPaid(false)
                    .build();
        } else {
            settlement = Settlement.builder()
                    .id(settlement.getId())
                    .settlement(orderIncome)
                    .owner(ownerRepository.findById(market.getOwnerId()).orElseThrow())
                    .settleDate(LocalDate.now())
                    .isPaid(false)
                    .build();
        }


        return settlementRepository.save(settlement);
    }

    @Transactional
    public List<Settlement> getSettlements() {
        return settlementRepository.findAllBySettleDate(LocalDate.now());
    }
}
