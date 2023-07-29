package com.example.storesettlement.services;

import com.example.storesettlement.dto.OwnerCreateDto;
import com.example.storesettlement.dto.OwnerEditDto;
import com.example.storesettlement.model.Market;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.model.enums.Role;
import com.example.storesettlement.repositories.MarketRepository;
import com.example.storesettlement.repositories.MemberRepository;
import com.example.storesettlement.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    @Transactional
    public Owner getOwnerDetail(Member member) {
        return ownerRepository.findByMember(member).orElse(null);
    }

    @Transactional
    public Owner addOwner(OwnerCreateDto ownerDto) {

        Member member = memberRepository.findByUsername(ownerDto.memberUsername())
                .orElseThrow(
                        () -> new IllegalStateException("해당 username의 계정이 존재하지 않습니다.")
                );

        if (member.getRole() != Role.OWNER) {
            throw new IllegalStateException("OWNER 계정이 아닙니다.");
        }

        if (ownerRepository.findByMember(member).isPresent()) {
            throw new IllegalStateException("이 계정의 Owner가 이미 존재합니다.");
        }

        Owner owner = Owner.builder()
                .name(ownerDto.name())
                .member(member)
                .market(null)
                .accountNum(ownerDto.accountNum())
                .build();
        return ownerRepository.save(owner);
    }

    @Transactional
    public List<Owner> ownerList() {
        return ownerRepository.findAll();
    }

    @Transactional
    public Owner addMarket(String ownerName, Market market) {
        Owner owner = ownerRepository.findByName(ownerName).orElseThrow();
        Owner newOwner = Owner.builder()
                .id(owner.getId())
                .name(owner.getName())
                .accountNum(owner.getAccountNum())
                .member(owner.getMember())
                .market(market)
                .build();
        return ownerRepository.save(newOwner);
    }

    public Owner editOwner(Long ownerId, OwnerEditDto ownerEditDto) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            throw new IllegalStateException("존재하지 않는 오너");
        }
        Owner newOwner = Owner.builder()
                .id(owner.getId())
                .market(owner.getMarket())
                .member(owner.getMember())
                .name(ownerEditDto.name())
                .accountNum(ownerEditDto.accountNum())
                .build();
        return ownerRepository.save(newOwner);
    }

    public void deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            throw new IllegalStateException("존재하지 않는 오너");
        }
        ownerRepository.delete(owner);
    }

    public void deleteMarketReference(Long marketId) {
        Market market = marketRepository.findById(marketId).orElseThrow();
        Owner owner = ownerRepository.findById(market.getOwnerId()).orElseThrow();
        Owner newOwner = Owner.builder()
                .id(owner.getId())
                .name(owner.getName())
                .member(owner.getMember())
                .accountNum(owner.getAccountNum())
                .market(null)
                .build();
        ownerRepository.save(newOwner);
    }
}
