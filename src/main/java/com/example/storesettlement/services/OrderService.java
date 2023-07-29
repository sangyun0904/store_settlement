package com.example.storesettlement.services;

import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.dto.MarketEditDto;
import com.example.storesettlement.dto.OrderCreateDto;
import com.example.storesettlement.dto.OrderEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.OrderInfo;
import com.example.storesettlement.model.enums.OrderState;
import com.example.storesettlement.repositories.MarketRepository;
import com.example.storesettlement.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public OrderInfo addOrder(OrderCreateDto orderDto) {

        OrderInfo orderInfo =  OrderInfo.builder()
                .orderNum(orderDto.orderNum())
                .product(orderDto.product())
                .price(orderDto.price())
                .customer(orderDto.customer())
                .market(marketRepository.findByName(orderDto.marketName()).orElseThrow(() -> new IllegalStateException(orderDto.marketName() + " 이름의 마켓이 존재하지 않습니다.")))
                .serviceCharge(Math.round(orderDto.price() * 0.1))
                .orderDate(LocalDate.now())
                .orderState(OrderState.ORDERED)
                .build();

        return orderRepository.save(orderInfo);
    }

    @Transactional
    public OrderInfo editOrder(Long orderNum, OrderEditDto orderDto) {
        System.out.println(orderNum);
        OrderInfo orderInfo = orderRepository.findByOrderNum(orderNum).orElseThrow();
        Market market = marketRepository.findByName(orderDto.marketName()).orElse(null);
        if (market == null) {
            throw new IllegalStateException(orderDto.marketName() + " 이름의 마켓이 존재하지 않습니다.");
        }

        OrderInfo newOrderInfo = OrderInfo.builder()
                .id(orderInfo.getId())
                .orderNum(orderInfo.getOrderNum())
                .product(orderDto.product())
                .price(orderDto.price())
                .customer(orderDto.customer())
                .market(market)
                .serviceCharge(Math.round(orderDto.price() * 0.1))
                .orderDate(orderInfo.getOrderDate())
                .orderState(orderInfo.getOrderState())
                .build();

        return orderRepository.save(newOrderInfo);
    }

    @Transactional
    public void deleteOrder(Long orderNum) {
        OrderInfo order = orderRepository.findByOrderNum(orderNum).orElseThrow();
        orderRepository.delete(order);
    }

    @Transactional
    public List<OrderInfo> getAllOrder() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    @Transactional
    public OrderInfo getOrder(Long orderNum) {
        return orderRepository.findByOrderNum(orderNum).orElseThrow();
    }

    public List<OrderInfo> getOrderByMarket(Market market) {
        return orderRepository.getAllByMarket(market);
    }
}
