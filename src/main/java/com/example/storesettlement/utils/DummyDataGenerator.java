package com.example.storesettlement.utils;

import com.example.storesettlement.dto.OrderCreateDto;
import com.example.storesettlement.dto.OwnerCreateDto;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.OrderInfo;
import com.example.storesettlement.repositories.MarketRepository;
import com.example.storesettlement.services.AuthenticationService;
import com.example.storesettlement.services.MemberService;
import com.example.storesettlement.services.OrderService;
import com.example.storesettlement.services.OwnerService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.example.storesettlement.model.enums.Role.*;

@Component
public class DummyDataGenerator {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final OwnerService ownerService;
    private final MarketRepository marketRepository;
    private final OrderService orderService;

    public DummyDataGenerator(MemberService memberService, AuthenticationService authenticationService, OwnerService ownerService, MarketRepository marketRepository, OrderService orderService) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.ownerService = ownerService;
        this.marketRepository = marketRepository;
        this.orderService = orderService;

        if (memberService.loadUserByUsername("admin") == null) {
            RegisterRequest request = new RegisterRequest("admin", "adminPass", "admin@gmail.com", ADMIN);
            authenticationService.register(request);
        }
        if (memberService.loadUserByUsername("settle") == null) {
            RegisterRequest request2 = new RegisterRequest("settle", "settlePass", "settle@gmail.com", SETTLE_TEAM);
            authenticationService.register(request2);
        }
        if (memberService.loadUserByUsername("owner") == null) {
            RegisterRequest request3 = new RegisterRequest("owner", "ownerPass", "owner@gmail.com", OWNER);
            authenticationService.register(request3);
        }

        Member member = memberService.loadUserByUsername("owner");
        if (ownerService.getOwnerDetail(member) == null) {
            OwnerCreateDto ownerDto = new OwnerCreateDto("no name", "000000000000", "owner");
            ownerService.addOwner(ownerDto);
        }

        Market market = marketRepository.findByName("no name market").orElse(null);
        if (market == null) {
            market = Market.builder()
                    .name("no name market")
                    .address("Mountain View, California, United States")
                    .phone("000-0000-0000")
                    .openDate(LocalDate.parse("1999-09-09"))
                    .uploadDate(LocalDate.now())
                    .settleDate(25)
                    .owner(ownerService.getOwnerDetail(member))
                    .build();
            marketRepository.save(market);
        }

        List<OrderInfo> orderList = orderService.getOrderByMarket(market);

        if (orderList.size() == 0) {
            OrderCreateDto orderDto1 = new OrderCreateDto((long) 10001, "product1", (long) 17000, "customer1", "no name market");
            orderService.addOrder(orderDto1);
            OrderCreateDto orderDto2 = new OrderCreateDto((long) 10002, "product2", (long) 20000, "customer1", "no name market");
            orderService.addOrder(orderDto2);
            OrderCreateDto orderDto3 = new OrderCreateDto((long) 10003, "product3", (long) 1000, "customer2", "no name market");
            orderService.addOrder(orderDto3);
        }
    }

}
