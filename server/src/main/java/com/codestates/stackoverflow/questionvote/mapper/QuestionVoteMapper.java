package com.codestates.stackoverflow.questionvote.mapper;

import com.codestates.stackoverflow.answervote.dto.AnswerVoteDto;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.questionvote.dto.QuestionVoteDto;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import org.springframework.stereotype.Component;

@Component
public class QuestionVoteMapper {

    public QuestionVote postToQuestionVote(QuestionVoteDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            QuestionVote questionVote = new QuestionVote();
            questionVote.setLike(postDto.isLike());
            questionVote.setMember(postToMember(postDto));
            questionVote.setQuestion(postToQuestion(postDto));

            return questionVote;
        }
    }

    public QuestionVoteDto.ResponseDto questionVoteToResponse(QuestionVote questionVote) {
        if(questionVote == null) {
            return null;
        }
        else {
            QuestionVoteDto.ResponseDto responseDto = new QuestionVoteDto.ResponseDto();
            responseDto.setQuestion_vote_id(questionVote.getQuestionVoteId());
            responseDto.setLike(questionVote.isLike());
            responseDto.setMember_id(questionVote.getMember().getMemberId());
            responseDto.setQuestion_id(questionVote.getQuestion().getQuestionId());

            return responseDto;
        }
    }

    public QuestionVoteDto.TotalVoteCountResponseDto questionVoteTotalVoteCountResponse(long totalVoteCount) {
        QuestionVoteDto.TotalVoteCountResponseDto responseDto = new QuestionVoteDto.TotalVoteCountResponseDto();
        responseDto.setTotalVoteCount(totalVoteCount);

        return responseDto;
    }

    private Member postToMember(QuestionVoteDto.PostDto postDto) {
        Member member = new Member();
        member.setMemberId(postDto.getMember_id());

        return member;
    }

    private Questions postToQuestion(QuestionVoteDto.PostDto postDto) {
        Questions question = new Questions();
        question.setQuestionId(postDto.getQuestion_id());

        return question;
    }
}
