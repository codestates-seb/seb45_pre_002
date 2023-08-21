package com.codestates.stackoverflow.answervote.mapper;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answervote.dto.AnswerVoteDto;
import com.codestates.stackoverflow.answervote.entity.AnswerVote;
import com.codestates.stackoverflow.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class AnswerVoteMapper {
    public AnswerVote postToAnswerVote(AnswerVoteDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            AnswerVote answerVote = new AnswerVote();
            answerVote.setLike(postDto.isLike());
            answerVote.setMember(postToMember(postDto));
            answerVote.setAnswer(postToAnswer(postDto));

            return answerVote;
        }
    }

    public AnswerVoteDto.ResponseDto answerVoteToResponse(AnswerVote answerVote) {
        if(answerVote == null) {
            return null;
        }
        else {
            AnswerVoteDto.ResponseDto responseDto = new AnswerVoteDto.ResponseDto();
            responseDto.setAnswer_vote_id(answerVote.getAnswerVoteId());
            responseDto.setLike(answerVote.isLike());
            responseDto.setMember_id(answerVote.getMember().getMemberId());
            responseDto.setAnswer_id(answerVote.getAnswer().getAnswerId());

            return responseDto;
        }
    }

    private Member postToMember(AnswerVoteDto.PostDto postDto) {
        Member member = new Member();
        member.setMemberId(postDto.getMember_id());

        return member;
    }

    private Answer postToAnswer(AnswerVoteDto.PostDto postDto) {
        Answer answer = new Answer();
        answer.setAnswerId(postDto.getAnswer_id());

        return answer;
    }
}
