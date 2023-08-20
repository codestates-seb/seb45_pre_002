package com.codestates.stackoverflow.answer.mapper;

import com.codestates.stackoverflow.answer.dto.AnswerDto;
import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.member.dto.MemberDto;
import com.codestates.stackoverflow.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public Answer postToAnswer(AnswerDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Answer answer = new Answer();
            answer.setBody(postDto.getBody());

            return answer;
        }
    }

    public Answer patchToAnswer(AnswerDto.PatchDto patchDto) {
        if(patchDto == null) {
            return null;
        }
        else {
            Answer answer = new Answer();
            answer.setBody(patchDto.getBody());

            return answer;
        }
    }

    public AnswerDto.ResponseDto answerToResponse(Answer answer) {
        if(answer == null) {
            return null;
        }
        else {
            AnswerDto.ResponseDto responseDto = new AnswerDto.ResponseDto();
            responseDto.setMemberId(answer.getMember().getMemberId());
            responseDto.setAnswerId(answer.getAnswerId());
            responseDto.setBody(answer.getBody());
//            responseDto.setAccepted(answer.getAccepted());
            responseDto.setLastModifiedAt(answer.getLastModifiedAt());

            return responseDto;
        }
    }
}
