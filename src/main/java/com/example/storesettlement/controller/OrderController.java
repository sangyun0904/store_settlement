package com.example.storesettlement.controller;

import com.example.storesettlement.dto.OrderCreateDto;
import com.example.storesettlement.dto.OrderEditDto;
import com.example.storesettlement.model.OrderInfo;
import com.example.storesettlement.services.OrderService;
import com.example.storesettlement.utils.DefaultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public DefaultResponse<List<OrderInfo>> orderList() {
        return DefaultResponse.res(200, "OK", orderService.getAllOrder());
    }

    @GetMapping("/{orderNum}")
    public DefaultResponse<OrderInfo> orderDetail(@PathVariable(value = "orderNum") Long orderNum) {
        return DefaultResponse.res(200, "OK", orderService.getOrder(orderNum));
    }

    @PostMapping
    public DefaultResponse<OrderInfo> orderCreate(@RequestBody OrderCreateDto orderCreateDto) {
        return DefaultResponse.res(201, "Created", orderService.addOrder(orderCreateDto));
    }

    @PatchMapping("/{orderNum}")
    public DefaultResponse<OrderInfo> orderUpdate(@PathVariable(value = "orderNum") Long orderNum, @RequestBody OrderEditDto orderEditDto) {
        return DefaultResponse.res(200, "OK", orderService.editOrder(orderNum, orderEditDto));
    }

    @DeleteMapping("/{orderNum}")
    public void orderDelete(@PathVariable(value = "orderNum") Long orderNum) {
        orderService.deleteOrder(orderNum);
    }

}
