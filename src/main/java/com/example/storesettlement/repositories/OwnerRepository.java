package com.example.storesettlement.repositories;

import com.example.storesettlement.model.Member;
import com.example.storesettlement.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByMember(Member member);
}
