package com.example.storesettlement.controller;

import com.example.storesettlement.dto.AuthenticationRequest;
import com.example.storesettlement.dto.AuthenticationResponse;
import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.services.AuthenticationService;
import com.example.storesettlement.services.MemberService;
import com.example.storesettlement.utils.DefaultResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.example.storesettlement.model.enums.Role.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final MemberService memberService;

    @ApiResponse(responseCode = "200", description = "계졍 추가", useReturnTypeSchema = true)
    @PostMapping("/register")
    public DefaultResponse<Member> register(
            @RequestBody RegisterRequest request
    ) {
        return DefaultResponse.res(200, "OK", authenticationService.register(request));
    }

    @ApiResponse(responseCode = "200", description = "로그인 성공", useReturnTypeSchema = true)
    @PostMapping("/authenticate")
    public DefaultResponse<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return DefaultResponse.res(200, "OK", authenticationService.authenticate(request));
    }

    @ApiResponse(responseCode = "200", description = "토큰 리프레시 성공", useReturnTypeSchema = true)
    @PostMapping("/refresh-token")
    public DefaultResponse<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        return DefaultResponse.res(200, "OK", authenticationService.refreshToken(request, response));
    }


    @ApiResponse(responseCode = "200", description = "계졍 삭제", useReturnTypeSchema = true)
    @DeleteMapping("/{name}")
    public DefaultResponse marketDelete(@PathVariable(value = "name") @Parameter(name = "name", description = "username") String name) {
        Member member = memberService.loadUserByUsername(name);
        memberService.deleteMember(member.getId());
        return DefaultResponse.res(200, "OK", null);
    }

    @ApiResponse(responseCode = "200", description = "계졍 리스트 조회", useReturnTypeSchema = true)
    @GetMapping
    public DefaultResponse<List<Member>> memberList() {
        return DefaultResponse.res(200, "OK", memberService.getAllMember());
    }

    @ApiResponse(responseCode = "200", description = "계졍 조회", useReturnTypeSchema = true)
    @GetMapping("/{name}")
    public DefaultResponse<Member> memberDetail(@PathVariable(value = "name") @Parameter(name = "name", description = "username") String name) {
        return DefaultResponse.res(200, "OK", memberService.getMemberDetail(name));
    }

}
