package com.example.storesettlement.controller;

import com.example.storesettlement.dto.OrderCreateDto;
import com.example.storesettlement.dto.OwnerCreateDto;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.OrderInfo;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.repositories.MarketRepository;
import com.example.storesettlement.services.*;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.example.storesettlement.model.enums.Role.*;

@RestController
@RequestMapping("/testdata")
@RequiredArgsConstructor
public class TestDataController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final OwnerService ownerService;
    private final MarketRepository marketRepository;
    private final OrderService orderService;

    @ApiResponse(responseCode = "200", description = "테스트 유저 생성", useReturnTypeSchema = true)
    @GetMapping()
    public DefaultResponse testUser() {
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
            OwnerCreateDto ownerDto = new OwnerCreateDto("no name", "no market", "000000000000", "owner");
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

        return DefaultResponse.res(200, "OK", "테스트 데이터 생성.");
    }

}
