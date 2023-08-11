package com.codestates.stackoverflow.member.service;

import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Member createMember(Member member) {
        verifyExistMember(member);
        return repository.save(member);
    }

    private void verifyExistMember(Member member) {
        //문제가 생기면 Exception 발생
    }
}
