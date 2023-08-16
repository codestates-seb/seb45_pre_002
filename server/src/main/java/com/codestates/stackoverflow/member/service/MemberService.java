package com.codestates.stackoverflow.member.service;

import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.repository.MemberRepository;
import com.codestates.stackoverflow.security.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final CustomAuthorityUtils authorityUtils;

    public Member createMember(Member member) {
        verifyExistMember(member);

        String encodedPassword = passwordEncoder.encode(member.getPassword());

        member.setPassword(encodedPassword);
        member.setRoles(authorityUtils.createRoles());

        return repository.save(member);
    }

    public boolean authenticateMember(String email, String password) {
        //TODO: email과 password를 기준으로 멤버 인증, 해당 메서드는 security 패키지로 이동하는게 좋아보임
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Member updateMember(Member member) {
        Member updateMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getEmail())
                .ifPresent(name -> updateMember.setEmail(name));
        Optional.ofNullable(member.getUsername())
                .ifPresent(phone -> updateMember.setUsername(phone));
        Optional.ofNullable(member.getPassword())
                .ifPresent(memberStatus -> updateMember.setPassword(memberStatus));
        return repository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {return findVerifiedMember(memberId); }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                repository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    private void verifyExistMember(Member info) {
        Optional<Member> member = repository.findByEmail(info.getEmail());
        //member에 repository에 저장된 이메일을 저장.
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        //TODO:member안에 해당하는 email이 없을 경우 에러 발생.
        //TODO:email이 없다면 당연히 해당 memberId에 저장될 username, password도 없기 때문에 이메일만 사용.
    }
}
