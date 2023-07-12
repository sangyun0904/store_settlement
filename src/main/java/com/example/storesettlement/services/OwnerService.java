package com.example.storesettlement.services;

import com.example.storesettlement.dto.OwnerCreateDto;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final MarketService marketService;
    private final MemberService memberService;

    @Transactional
    public Owner getOwnerDetail(Member member) {
        return ownerRepository.findByMember(member).orElse(null);
    }

    @Transactional
    public Owner addOwner(Member member, OwnerCreateDto ownerDto) {

        if (ownerRepository.findByMember(member).isPresent()) {
            throw new IllegalStateException("이 계정의 Owner가 이미 존재합니다.");
        }

        Owner owner = Owner.builder()
                .name(ownerDto.name())
                .market(null)
                .accountNum(ownerDto.accountNum())
                .member(memberService.loadUserByUsername(ownerDto.username()))
                .build();
        return ownerRepository.save(owner);
    }

    @Transactional
    public List<Owner> ownerList() {
        return ownerRepository.findAll();
    }
}
