package com.codestates.stackoverflow.member.controller;

import com.codestates.stackoverflow.mail.EmailDto;

import com.codestates.stackoverflow.mail.EmailController;
import com.codestates.stackoverflow.member.dto.MemberDto;
import com.codestates.stackoverflow.member.mapper.MemberMapper;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codestates.stackoverflow.member.entity.Member;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController{

    private final static String USER_DEFAULT_URL = "/members";

    private final MemberMapper mapper;

    private final MemberService service;

    @GetMapping("/login")
    public String loginForm() { // 로그인 폼 요청 메서드
        return "login";
    }

    @GetMapping("/signup") //로컬 테스트용 추후 배포 시 삭제
    public String signupForm() {
        return"signup";
    }

    @PostMapping("/signup")
    public ResponseEntity postMember(@RequestBody MemberDto.PostDto postDto) {
        Member member = mapper.postToMember(postDto);

        member = service.createMember(member);

        URI location = UriCreator.createUri(USER_DEFAULT_URL, member.getMemberId());

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PatchMapping("/{member_id}")
    public ResponseEntity patchMember (@PathVariable("member_id") @Positive long memberId, @Valid @RequestBody MemberDto.PatchDto Member) {
        Member.setMemberId(memberId);

        Member response =service.updateMember(mapper.patchToMember(Member));

        return new ResponseEntity<>(mapper.memberToResponse(response), HttpStatus.OK) ;
    }

    @GetMapping("/{member_id}")
    public ResponseEntity getMember(
            @PathVariable("member_id") @Positive long memberId) {
        Member member = service.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToResponse(member), HttpStatus.OK);
    }

    @PostMapping("/jwtTest") // jwt 테스트용 추후 삭제
    public String jwtTest() {
        return "test passed";
    }
}
