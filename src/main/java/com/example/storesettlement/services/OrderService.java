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

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MarketRepository marketRepository;

    public OrderInfo addOrder(OrderCreateDto orderDto) {

        OrderInfo orderInfo =  OrderInfo.builder()
                .orderNum(orderDto.orderNum())
                .product(orderDto.product())
                .price(orderDto.price())
                .customer(orderDto.customer())
                .market(marketRepository.findByName(orderDto.marketName()).orElseThrow())
                .serviceCharge(Math.round(orderDto.price() * 0.1))
                .orderDate(LocalDate.now())
                .orderState(OrderState.ORDERED)
                .build();

        return orderRepository.save(orderInfo);
    }

    public OrderInfo editOrder(Long orderNum, OrderEditDto orderDto) {
        OrderInfo orderInfo = orderRepository.findByOrderNum(orderNum).orElseThrow();

        OrderInfo newOrderInfo = OrderInfo.builder()
                .orderNum(orderInfo.getOrderNum())
                .product(orderDto.product())
                .price(orderDto.price())
                .customer(orderDto.customer())
                .market(marketRepository.findByName(orderDto.marketName()).orElseThrow())
                .serviceCharge(Math.round(orderDto.price() * 0.1))
                .orderDate(orderInfo.getOrderDate())
                .orderState(orderInfo.getOrderState())
                .build();

        return orderRepository.save(newOrderInfo);
    }

    public void deleteOrder(Long orderNum) {
        OrderInfo order = orderRepository.findByOrderNum(orderNum).orElseThrow();
        orderRepository.delete(order);
    }

    public List<OrderInfo> getAllOrder() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    public OrderInfo getOrder(Long orderNum) {
        return orderRepository.findByOrderNum(orderNum).orElseThrow();
    }
}
