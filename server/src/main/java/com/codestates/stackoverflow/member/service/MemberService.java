package com.codestates.stackoverflow.member.service;

import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member) {
        verifyExistMember(member);
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        return repository.save(member);
    }

    public boolean authenticateMember(String email, String password) {
        //TODO: email과 password를 기준으로 멤버 인증, 해당 메서드는 security 패키지로 이동하는게 좋아보임
        return true;
    }

    private void verifyExistMember(Member member) {
        //문제가 생기면 Exception 발생
    }
}
