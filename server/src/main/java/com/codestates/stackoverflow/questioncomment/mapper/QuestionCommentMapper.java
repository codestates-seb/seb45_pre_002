package com.codestates.stackoverflow.questioncomment.mapper;

import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.questioncomment.dto.QuestionCommentDto;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.utils.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class QuestionCommentMapper {

    public QuestionComment postToQuestionComment(QuestionCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            QuestionComment questionComment = new QuestionComment();
            questionComment.setBody(postDto.getBody());
            questionComment.setQuestion(postToQuestion(postDto));
            questionComment.setMember(postToMember(postDto));

            return questionComment;
        }
    }

    public QuestionComment patchToQuestionComment(QuestionCommentDto.PatchDto patchDto) {
        if(patchDto == null) {
            return null;
        }
        else {
            QuestionComment questionComment = new QuestionComment();
            questionComment.setBody(patchDto.getBody());

            return questionComment;
        }
    }

    public QuestionComment deleteToQuestionComment(QuestionCommentDto.DeleteDto deleteDto) {
        if(deleteDto == null) {
            return null;
        }
        else {
            QuestionComment questionComment = new QuestionComment();
            questionComment.setQuestionCommentId(deleteDto.getQuestion_comment_id());
            questionComment.setMember(deleteToMember(deleteDto));
            return questionComment;
        }
    }

    public QuestionCommentDto.ResponseDto questionCommentToResponse(QuestionComment questionComment) {
        if(questionComment == null) {
            return null;
        }
        else {
            QuestionCommentDto.ResponseDto responseDto = new QuestionCommentDto.ResponseDto();
            responseDto.setQuestion_comment_id(questionComment.getQuestionCommentId());
            responseDto.setBody(questionComment.getBody());
            responseDto.setCreated_at(questionComment.getCreatedAt());
            responseDto.setLast_modified_at(questionComment.getLastModifiedAt());

            return responseDto;
        }
    }

    public QuestionCommentDto.PageResponseDto questionCommentPageToPageResponseDto(Page<QuestionComment> questionComments) {
        if(questionComments == null) {
            return null;
        }
        else {
            QuestionCommentDto.PageResponseDto pageResponseDto = new QuestionCommentDto.PageResponseDto();

            pageResponseDto.setData(questionComments.getContent());
            pageResponseDto.setPageInfo(PageInfo.builder()
                    .pageNumber(questionComments.getNumber())
                    .pageSize(questionComments.getSize())
                    .totalElements(questionComments.getTotalElements())
                    .totalPages(questionComments.getTotalPages())
                    .build());

            return pageResponseDto;
        }
    }

    private Questions postToQuestion(QuestionCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Questions question = new Questions();
            question.setQuestionId(postDto.getQuestion_id());

            return question;
        }
    }

    private Member postToMember(QuestionCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Member member = new Member();
            member.setMemberId(postDto.getMember_id());

            return member;
        }
    }

    private Member deleteToMember(QuestionCommentDto.DeleteDto deleteDto) {
        if(deleteDto == null) {
            return null;
        }
        else {
            Member member = new Member();
            member.setMemberId(deleteDto.getMember_id());

            return member;
        }
    }
}