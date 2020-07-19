package com.yeongjae.damoim.domain.member.controller;

import com.yeongjae.damoim.domain.member.dto.MemberCreateDto;
import com.yeongjae.damoim.domain.member.dto.MemberGetDto;
import com.yeongjae.damoim.domain.member.dto.MemberSignInDto;
import com.yeongjae.damoim.domain.member.service.MemberCreateService;
import com.yeongjae.damoim.domain.member.service.MemberSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/damoim/sign")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignInService memberSignInService;
    private final MemberCreateService memberCreateService;

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberCreateDto memberCreateDto){
        MemberGetDto savedMember = memberCreateService.createMember(memberCreateDto);
        return ResponseEntity.created(URI.create("/damoim/sign/" + savedMember.getId())).body(savedMember);
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody MemberSignInDto memberSignInDto){
        return memberSignInService.signIn(memberSignInDto);
    }
}
