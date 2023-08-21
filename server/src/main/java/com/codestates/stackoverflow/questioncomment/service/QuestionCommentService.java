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

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionCommentService {

    //임시로 사용중인 QuestionsRepository 추후 questionsService 구현 완료 시 삭제
    private final QuestionsRepository questionsRepository;
    private final QuestionsService questionsService;

    private final MemberService memberService;

    private final QuestionCommentRepository questionCommentRepository;

    public QuestionComment createQuestionComment(QuestionComment questionComment) {
        //멤버 검증
        Member findMember = memberService.findVerifiedMember(questionComment.getMember().getMemberId());

        //Answer findAnswer = answerService.findVerifyAnswer(answerComment.getAnswer().getAnswerId());
        //AnswerService 구현 완료되면 위의 검증 코드로 수정
        Questions findQuestion = questionsRepository.findById(questionComment.getQuestion().getQuestionId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

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

    public Page<QuestionComment> findQuestionComments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("questionCommentId").ascending());
        Page<QuestionComment> questionComments = questionCommentRepository.findAll(pageable);

        //TODO: soft delete 구현했을때 boolean isDelete 가 true 인 객체는 리스트에서 제외하는 기능 추가

        return questionComments;
    }

    public void deleteQuestionComment(long questionCommentId) {
        //TODO: 남는 시간 보고 soft delete 구현하기
        QuestionComment questionComment = findVerifiedQuestionComment(questionCommentId);

        questionCommentRepository.delete(questionComment);
    }

    private QuestionComment findVerifiedQuestionComment(long questionCommentId) {
        QuestionComment questionComment = questionCommentRepository.findById(questionCommentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return questionComment;
    }
}