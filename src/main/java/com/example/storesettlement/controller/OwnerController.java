package com.example.storesettlement.controller;

import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.services.JwtService;
import com.example.storesettlement.services.MemberService;
import com.example.storesettlement.services.OwnerService;
import com.example.storesettlement.utils.DefaultResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final OwnerService ownerService;

    @GetMapping("/ownerdetail")
    public DefaultResponse<Owner> ownerDetail(
            @NonNull HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);


        String username = jwtService.extractUsername(jwt);

        Member member = memberService.loadUserByUsername(username);

        return DefaultResponse.res(201, "Created", ownerService.getOwnerDetail(member));

    }
}
