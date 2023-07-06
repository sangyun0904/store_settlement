package com.example.storesettlement.services;

import com.example.storesettlement.controller.AuthenticationController;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerServiceTest {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    OwnerService ownerService;
    @Autowired
    MemberService memberService;

    private Member member;
    private Owner owner;

    @BeforeAll
    void beforeAll() {
        authenticationController.testUser();
        member = memberService.loadUserByUsername("owner");
        owner = Owner.builder()
                .accountNum("000000000")
                .market(null)
                .member(member)
                .name("상윤")
                .build();
        ownerService.addOwner(owner);
    }

    @Test
    void getOwnerDetail() {
        Owner owner1 = ownerService.getOwnerDetail(member);
        assertThat(owner1.getId()).isEqualTo(owner.getId());
    }
}