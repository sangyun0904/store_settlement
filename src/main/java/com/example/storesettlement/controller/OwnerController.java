package com.example.storesettlement.controller;

import com.example.storesettlement.dto.*;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.services.JwtService;
import com.example.storesettlement.services.MemberService;
import com.example.storesettlement.services.OwnerService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.storesettlement.model.enums.Role.*;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final OwnerService ownerService;

    @ApiResponse(responseCode = "200", description = "업주 상세 조회", useReturnTypeSchema = true)
    @GetMapping("/ownerdetail")
    public DefaultResponse<Owner> ownerDetail(
            @NonNull HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);


        String username = jwtService.extractUsername(jwt);

        Member member = memberService.loadUserByUsername(username);

        return DefaultResponse.res(200, "OK", ownerService.getOwnerDetail(member));

    }

    @ApiResponse(responseCode = "200", description = "점주 추가", useReturnTypeSchema = true)
    @PostMapping
    public DefaultResponse<Owner> marketCreate(@RequestBody OwnerCreateDto ownerDto) {

        return DefaultResponse.res(200, "OK", ownerService.addOwner(ownerDto));
    }

    @ApiResponse(responseCode = "200", description = "점주 리스트 조회", useReturnTypeSchema = true)
    @GetMapping
    public DefaultResponse<List<Owner>> marketList() {
        return DefaultResponse.res(200, "OK", ownerService.ownerList());
    }
    @ApiResponse(responseCode = "200", description = "점주 수정", useReturnTypeSchema = true)
    @PatchMapping("/{ownerId}")
    public DefaultResponse<Owner> marketUpdate(@PathVariable(value = "ownerId") @Parameter(name = "ownerId", description = "점주 id") Long ownerId , @RequestBody OwnerEditDto ownerEditDto) {
        return DefaultResponse.res(200, "OK", ownerService.editOwner(ownerId, ownerEditDto));
    }

    @ApiResponse(responseCode = "200", description = "점주 삭제", useReturnTypeSchema = true)
    @DeleteMapping("/{ownerId}")
    public DefaultResponse marketDelete(@PathVariable(value = "ownerId") @Parameter(name = "ownerId", description = "점주 id") Long ownerId) {
        ownerService.deleteOwner(ownerId);
        return DefaultResponse.res(200, "OK");
    }


}
