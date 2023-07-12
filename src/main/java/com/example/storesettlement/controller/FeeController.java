package com.example.storesettlement.controller;

import com.example.storesettlement.dto.FeeDto;
import com.example.storesettlement.dto.MarketCreateDto;
import com.example.storesettlement.model.Fee;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.services.FeeService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    @ApiResponse(responseCode = "200", description = "수수료 조회", useReturnTypeSchema = true)
    @GetMapping
    public DefaultResponse<Fee> marketList() {
        return DefaultResponse.res(200, "OK", feeService.getFee());
    }

    @ApiResponse(responseCode = "200", description = "수수료 생성 및 수정", useReturnTypeSchema = true)
    @PostMapping
    public DefaultResponse<Fee> marketCreate(@RequestBody FeeDto feeDto) {
        return DefaultResponse.res(200, "OK", feeService.setFee(feeDto));
    }

}
