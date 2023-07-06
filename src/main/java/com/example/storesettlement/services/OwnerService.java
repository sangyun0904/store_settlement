package com.example.storesettlement.services;

import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import com.example.storesettlement.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    public Owner getOwnerDetail(Member member) {
        return ownerRepository.findByMember(member).orElse(null);
    }

    @Transactional
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
