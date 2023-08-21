package com.codestates.stackoverflow.answercomment.mapper;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answercomment.dto.AnswerCommentDto;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.utils.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AnswerCommentMapper {

    public AnswerComment postToAnswerComment(AnswerCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            AnswerComment answerComment = new AnswerComment();
            answerComment.setBody(postDto.getBody());
            answerComment.setMember(postToMember(postDto));
            answerComment.setAnswer(postToAnswer(postDto));

            return answerComment;
        }
    }

    public AnswerComment patchToAnswerComment(AnswerCommentDto.PatchDto patchDto) {
        if(patchDto == null) {
            return null;
        }
        else {
            AnswerComment answerComment = new AnswerComment();
            answerComment.setBody(patchDto.getBody());

            return answerComment;
        }
    }

    public AnswerCommentDto.ResponseDto answerCommentToResponse(AnswerComment answerComment) {
        if(answerComment == null) {
            return null;
        }
        else {
            AnswerCommentDto.ResponseDto responseDto = new AnswerCommentDto.ResponseDto();
            responseDto.setAnswer_comment_id(answerComment.getAnswerCommentId());
            responseDto.setBody(answerComment.getBody());
            responseDto.setCreated_at(answerComment.getCreatedAt());
            responseDto.setLast_modified_at(answerComment.getLastModifiedAt());

            return responseDto;
        }
    }

    public AnswerCommentDto.PageResponseDto answerCommentPageToPageResponseDto(Page<AnswerComment> answerComments) {
        if(answerComments == null) {
            return null;
        }
        else {
            AnswerCommentDto.PageResponseDto pageResponseDto = new AnswerCommentDto.PageResponseDto();

            pageResponseDto.setData(answerComments.getContent());
            pageResponseDto.setPageInfo(PageInfo.builder()
                    .pageNumber(answerComments.getNumber())
                    .pageSize(answerComments.getSize())
                    .totalElements(answerComments.getTotalElements())
                    .totalPages(answerComments.getTotalPages())
                    .build());

            return pageResponseDto;
        }
    }

    private Member postToMember(AnswerCommentDto.PostDto postDto) {
        Member member = new Member();
        member.setMemberId(postDto.getMember_id());

        return member;
    }

    private Answer postToAnswer(AnswerCommentDto.PostDto postDto) {
        Answer answer = new Answer();
        answer.setAnswerId(postDto.getAnswer_id());

        return answer;
    }
}
