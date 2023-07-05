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

	public static void main(String[] args) {
		SpringApplication.run(StoreSettlementApplication.class, args);
	}

}
