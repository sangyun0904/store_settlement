package com.example.storesettlement.services;

import com.example.storesettlement.dto.MemberEditDto;
import com.example.storesettlement.model.Member;
import com.example.storesettlement.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username)
                .orElseGet(null);
    }

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

    public void deleteMember(Long id) {
        memberRepository.delete(memberRepository.findById(id).orElseThrow());
    }
}
