package com.example.storesettlement.services;

import com.example.storesettlement.controller.AuthenticationController;
import com.example.storesettlement.controller.TestDataController;
import com.example.storesettlement.dto.OwnerCreateDto;
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
    private AuthenticationController authenticationController;

    @Autowired
    private TestDataController testDataController;

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private MemberService memberService;

    private Member member;
    private OwnerCreateDto ownerDto;

    @BeforeAll
    void beforeAll() {
        testDataController.testUser();
        member = memberService.loadUserByUsername("owner");
        ownerDto = new OwnerCreateDto("상윤", "000000000", "owner");
        ownerService.addOwner(member, ownerDto);
    }

    @Test
    void getOwnerDetail() {
        Owner owner1 = ownerService.getOwnerDetail(member);
        assertThat(owner1.getName()).isEqualTo(ownerDto.name());
    }
}