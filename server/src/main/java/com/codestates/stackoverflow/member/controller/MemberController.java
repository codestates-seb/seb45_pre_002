package com.codestates.stackoverflow.member.controller;

import com.codestates.stackoverflow.member.dto.MemberDto;
import com.codestates.stackoverflow.member.mapper.MemberMapper;
import com.codestates.stackoverflow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codestates.stackoverflow.member.entity.Member;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberMapper mapper;

    private final MemberService service;

    @PostMapping
    public ResponseEntity postMember(@RequestBody MemberDto.PostDto postDto) {
        Member member = mapper.postToMember(postDto);

        member = service.createMember(member);

        MemberDto.ResponseDto response = mapper.memberToResponse(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
