package com.codestates.stackoverflow.member.controller;

import com.codestates.stackoverflow.member.dto.MemberDto;
import com.codestates.stackoverflow.member.mapper.MemberMapper;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.security.provider.JwtTokenProvider;
import com.codestates.stackoverflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.codestates.stackoverflow.member.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController{

    private final MemberMapper mapper;

    private final MemberService service;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.PostDto postDto) {
        Member member = mapper.postToMember(postDto);

        member = service.createMember(member);

        MemberDto.ResponseDto response = mapper.memberToResponse(member);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String jws = request.getHeader("Authorization");

        if (jwtTokenProvider.isTokenInBlackList(jws)) {
            return ResponseEntity.badRequest().body("Token has already been invalidated.");
        }

        jwtTokenProvider.addToUsedToken(jws); // 사용된 토큰목록에 토큰 추가

        return ResponseEntity.ok().body("Successfully logged out.");
    }

    @PatchMapping("/{member_id}")
    public ResponseEntity patchMember (@PathVariable("member_id") @Positive long memberId, @Valid @RequestBody MemberDto.PatchDto patchDto) {
        Member member = mapper.patchToMember(patchDto);

        member.setMemberId(memberId);

        Member response = service.updateMember(member);

        return new ResponseEntity<>(mapper.memberToResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/{member-id}/change-password")
    public ResponseEntity changePassword(@PathVariable("member-id") @Positive long memberId,
                                         @RequestBody @Valid MemberDto.ChangePasswordDto changePasswordDto) {
        Member member = service.changePassword(memberId, changePasswordDto.getOld_password(), changePasswordDto.getNew_password());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/{member_id}")
    public ResponseEntity getMember(
            @PathVariable("member_id") @Positive long memberId) {
        Member member = service.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToResponse(member), HttpStatus.OK);
    }

    @PostMapping("/jwtTest") // jwt 테스트용 추후 삭제
    public ResponseEntity jwtTest() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
