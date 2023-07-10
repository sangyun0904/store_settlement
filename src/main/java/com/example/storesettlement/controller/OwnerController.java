package com.example.storesettlement.controller;

import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.services.JwtService;
import com.example.storesettlement.services.MemberService;
import com.example.storesettlement.services.OwnerService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
