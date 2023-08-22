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

import java.util.List;

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

    public List<AnswerComment> findAnswerComments(long questionId, long answerId) {
        List<AnswerComment> answerComments = answerCommentRepository.findByAnswerAnswerIdOrderByAnswerCommentIdDesc(answerId);

        return answerComments;
    }

    public void deleteAnswerComment(AnswerComment answerComment) {
        AnswerComment findAnswerComment = findVerifiedAnswerComment(answerComment.getAnswerCommentId());

        if(findAnswerComment.getMember().getMemberId() != answerComment.getMember().getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }
        else {
            answerCommentRepository.delete(answerComment);
        }
    }

    private AnswerComment findVerifiedAnswerComment(long answerCommentId) {
        AnswerComment answerComment = answerCommentRepository.findById(answerCommentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return answerComment;
    }
}
