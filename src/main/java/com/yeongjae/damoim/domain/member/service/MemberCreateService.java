package com.yeongjae.damoim.domain.member.service;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.DuplicatedEmailException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberGetDto createMember(MemberCreateDto memberCreateDto) {
        if(memberRepository.existsByEmail(memberCreateDto.getEmail()))
            throw new DuplicatedEmailException();

        memberCreateDto.setPassword(passwordEncoder.encode(memberCreateDto.getPassword()));

        Member savedMember = memberRepository.save(memberCreateDto.of());

        return MemberGetDto.toDto(savedMember);
    }
}
