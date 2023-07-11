package com.example.storesettlement.controller;

import com.example.storesettlement.dto.OrderCreateDto;
import com.example.storesettlement.dto.OrderEditDto;
import com.example.storesettlement.model.OrderInfo;
import com.example.storesettlement.services.OrderService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ApiResponse(responseCode = "200", description = "주문 리스트 조히", useReturnTypeSchema = true)
    @GetMapping
    public DefaultResponse<List<OrderInfo>> orderList() {
        return DefaultResponse.res(200, "OK", orderService.getAllOrder());
    }

    @ApiResponse(responseCode = "200", description = "주문 상세 조회", useReturnTypeSchema = true)
    @GetMapping("/{orderNum}")
    public DefaultResponse<OrderInfo> orderDetail(@PathVariable(value = "orderNum") @Parameter(name = "orderNum", description = "주문 번호") Long orderNum) {
        return DefaultResponse.res(200, "OK", orderService.getOrder(orderNum));
    }

    @ApiResponse(responseCode = "200", description = "주문 생성", useReturnTypeSchema = true)
    @PostMapping
    public DefaultResponse<OrderInfo> orderCreate(@RequestBody OrderCreateDto orderCreateDto) {
        return DefaultResponse.res(200, "OK", orderService.addOrder(orderCreateDto));
    }

    @ApiResponse(responseCode = "200", description = "주문 수정", useReturnTypeSchema = true)
    @PatchMapping("/{orderNum}")
    public DefaultResponse<OrderInfo> orderUpdate(@PathVariable(value = "orderNum") @Parameter(name = "orderNum", description = "주문 번호")  Long orderNum, @RequestBody OrderEditDto orderEditDto) {
        return DefaultResponse.res(200, "OK", orderService.editOrder(orderNum, orderEditDto));
    }

    @ApiResponse(responseCode = "200", description = "주문 삭제", useReturnTypeSchema = true)
    @DeleteMapping("/{orderNum}")
    public DefaultResponse orderDelete(@PathVariable(value = "orderNum") Long orderNum) {
        orderService.deleteOrder(orderNum);
        return DefaultResponse.res(200, "OK");
    }

}
