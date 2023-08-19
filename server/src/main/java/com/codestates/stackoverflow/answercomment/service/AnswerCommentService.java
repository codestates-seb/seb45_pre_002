package com.codestates.stackoverflow.answercomment.service;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answer.repository.AnswerRepository;
import com.codestates.stackoverflow.answer.service.AnswerService;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.answercomment.repository.AnswerCommentRepository;
import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
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
public class AnswerCommentService {

    //임시로 사용중인 AnswerRepository 추후 answerService 구현 완료 시 삭제
    private final AnswerRepository answerRepository;
    private final AnswerService answerService;

    private final MemberService memberService;

    private final AnswerCommentRepository answerCommentRepository;

    public AnswerComment createAnswerComment(AnswerComment answerComment) {
        //멤버 검증
        Member findMember = memberService.findVerifiedMember(answerComment.getMember().getMemberId());

        //Answer findAnswer = answerService.findVerifyAnswer(answerComment.getAnswer().getAnswerId());
        //AnswerService 구현 완료되면 위의 검증 코드로 수정
        Answer findAnswer = answerRepository.findById(answerComment.getAnswer().getAnswerId())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        answerComment.setMember(findMember);
        answerComment.setAnswer(findAnswer);

        findAnswer.getAnswerComments().add(answerComment);

        return answerCommentRepository.save(answerComment);
    }

    public AnswerComment updateAnswerComment(AnswerComment answerComment) {
        AnswerComment findAnswerComment = findVerifiedAnswerComment(answerComment.getAnswerCommentId());

        findAnswerComment.setBody(answerComment.getBody());

        return findAnswerComment;
    }

    public AnswerComment findAnswerComment(long answerCommentId) {
        return findVerifiedAnswerComment(answerCommentId);
    }

    public Page<AnswerComment> findAnswerComments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("answerCommentId").ascending());
        Page<AnswerComment> answerComments = answerCommentRepository.findAll(pageable);

        //TODO: soft delete 구현했을때 boolean isDelete 가 true 인 객체는 리스트에서 제외하는 기능 추가

        return answerComments;
    }

    public void deleteAnswerComment(long answerCommentId) {
        //TODO: 남는 시간 보고 soft delete 구현하기
        AnswerComment answerComment = findVerifiedAnswerComment(answerCommentId);

        answerCommentRepository.delete(answerComment);
    }

    private AnswerComment findVerifiedAnswerComment(long answerCommentId) {
        AnswerComment answerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return answerComment;
    }
}
