package com.codestates.stackoverflow.answer.mapper;

import com.codestates.stackoverflow.answer.dto.AnswerDto;
import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.member.dto.MemberDto;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.entity.Questions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {

    public Answer postToAnswer(AnswerDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Answer answer = new Answer();
            answer.setMember(postToMember(postDto));
            answer.setQuestion(postToQuestion(postDto));
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
            responseDto.setMember_id(answer.getMember().getMemberId());
            responseDto.setAnswer_id(answer.getAnswerId());
            responseDto.setBody(answer.getBody());
//            responseDto.setAccepted(answer.getAccepted());
            responseDto.setCreated_at(answer.getCreatedAt());
            responseDto.setLast_modified_at(answer.getLastModifiedAt());

            return responseDto;
        }
    }

    private Member postToMember(AnswerDto.PostDto postDto) {
        Member member = new Member();
        member.setMemberId(postDto.getMember_id());

        return member;
    }

    private Questions postToQuestion(AnswerDto.PostDto postDto) {
        Questions questions = new Questions();
        questions.setQuestionId(postDto.getQuestion_id());

        return questions;
    }


    public AnswerDto.ListResponseDto answersToListResponseDto(List<Answer> answers) {
        if(answers == null) {
            return null;
        } else {
            List<AnswerDto.ResponseDto> responseDtos = new ArrayList<>();

            for(int i = 0; i < answers.size(); i++) {
                responseDtos.add(answerToResponse(answers.get(i)));
            }

            AnswerDto.ListResponseDto listResponseDto = new AnswerDto.ListResponseDto();
            listResponseDto.setAnswers(responseDtos);

            return listResponseDto;
        }

    }
}
