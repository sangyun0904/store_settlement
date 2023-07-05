package com.example.storesettlement;

import com.example.storesettlement.dto.RegisterRequest;
import com.example.storesettlement.repositories.MemberRepository;
import com.example.storesettlement.services.AuthenticationService;
import com.example.storesettlement.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.storesettlement.model.enums.Role.*;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreSettlementApplication {

	private static AuthenticationService authenticationService;
	private static MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(StoreSettlementApplication.class, args);
		if (memberService.loadUserByUsername("admin") == null){
			RegisterRequest request1 = new RegisterRequest("admin", "adminPass", "admin@gmail.com", ADMIN);
			RegisterRequest request2 = new RegisterRequest("settle", "settlePass", "settle@gmail.com", SETTLE_TEAM);
			RegisterRequest request3 = new RegisterRequest("owner", "ownerPass", "owner@gmail.com", OWNER);
			authenticationService.register(request1);
			authenticationService.register(request2);
			authenticationService.register(request3);
		}
	}

}
