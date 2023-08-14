package com.codestates.stackoverflow.member.mapper;

import com.codestates.stackoverflow.member.dto.MemberDto;
import org.springframework.stereotype.Component;
import com.codestates.stackoverflow.member.entity.Member;

@Component
public class MemberMapper {

    public Member postToMember(MemberDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Member member = new Member();
            member.setEmail(postDto.getEmail());
            member.setPassword(postDto.getPassword());
            member.setUsername(postDto.getUsername());

            return member;
        }
    }

    public MemberDto.ResponseDto memberToResponse(Member member) {
        if(member == null) {
            return null;
        }
        else {
            MemberDto.ResponseDto responseDto = new MemberDto.ResponseDto();
            responseDto.setMember_id(member.getMember_id());
            responseDto.setEmail(member.getEmail());
            responseDto.setPassword(member.getPassword());
            responseDto.setUsername(member.getUsername());

            return responseDto;
        }
    }
}
