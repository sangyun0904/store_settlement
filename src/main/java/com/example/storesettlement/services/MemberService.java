package com.example.storesettlement.services;

import com.example.storesettlement.dto.MemberEditDto;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElse(null);
    }

    @Transactional
    public Member editMember(String username, MemberEditDto memberEdit) {
        Member member = memberRepository.findByUsername(username).orElseThrow();

        Member newMember = Member.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(memberEdit.email())
                .role(member.getRole())
                .createdDate(member.getCreatedDate())
                .build();
        return memberRepository.save(newMember);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id).orElseThrow());
    }

    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    public Member getMemberDetail(String name) {
        Member member = memberRepository.findByUsername(name).orElse(null);
        if (member == null) {
            throw new IllegalStateException(name + " 아이디의 계정이 존재하지 않습니다.");
        }
        return member;
    }
}
