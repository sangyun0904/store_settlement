package com.example.storesettlement.services;

import com.example.storesettlement.model.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void getAllMember() {
        List<Member> memberList = memberService.getAllMember();
        assertThat(memberList.size()).isEqualTo(3);
    }
}