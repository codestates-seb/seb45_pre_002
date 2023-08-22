package com.codestates.stackoverflow.questioncomment.service;

import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import com.codestates.stackoverflow.question.service.QuestionsService;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.questioncomment.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionCommentService {

    private final QuestionsService questionsService;

    private final MemberService memberService;

    private final QuestionCommentRepository questionCommentRepository;

    public QuestionComment createQuestionComment(QuestionComment questionComment) {
        //한 멤버가 여러개의 댓글을 작성하지 못하게 하는 로직
        if(questionCommentRepository.findByQuestionQuestionIdAndMemberMemberId(questionComment.getQuestion().getQuestionId(), questionComment.getMember().getMemberId()).isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_EXISTS);
        };

        Member findMember = memberService.findVerifiedMember(questionComment.getMember().getMemberId());

        Questions findQuestion = questionsService.findVerifiedQuestionById(questionComment.getQuestion().getQuestionId());

        questionComment.setMember(findMember);
        questionComment.setQuestion(findQuestion);

        findQuestion.getQuestionComments().add(questionComment);

        return questionCommentRepository.save(questionComment);
    }

    public QuestionComment updateQuestionComment(QuestionComment questionComment) {
        QuestionComment findQuestionComment = findVerifiedQuestionComment(questionComment.getQuestionCommentId());

        findQuestionComment.setBody(questionComment.getBody());

        return findQuestionComment;
    }

    public QuestionComment findQuestionComment(long questionCommentId) {
        return findVerifiedQuestionComment(questionCommentId);
    }

    public List<QuestionComment> findQuestionComments(long questionId) {
        List<QuestionComment> questionComments = questionCommentRepository.findByQuestionQuestionIdOrderByQuestionCommentIdDesc(questionId);

        return questionComments;
    }

    public void deleteQuestionComment(QuestionComment questionComment) {

        QuestionComment findQuestionComment = findVerifiedQuestionComment(questionComment.getQuestionCommentId());

        if(findQuestionComment.getMember().getMemberId() != questionComment.getMember().getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        else {
            questionCommentRepository.delete(questionComment);
        }
    }

    private QuestionComment findVerifiedQuestionComment(long questionCommentId) {
        QuestionComment questionComment = questionCommentRepository.findById(questionCommentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return questionComment;
    }
}