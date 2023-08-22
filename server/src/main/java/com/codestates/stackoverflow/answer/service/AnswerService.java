package com.codestates.stackoverflow.answer.service;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates.stackoverflow.answer.repository.AnswerRepository;
import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import com.codestates.stackoverflow.question.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.awt.desktop.QuitStrategy;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    @Autowired
    private final AnswerRepository answerRepository;

    private final MemberService memberService;

    private final QuestionsRepository questionsRepository;

    private final QuestionsService questionService;

    private final AnswerMapper answerMapper;

    public Answer createAnswer(Answer answer) {

        Member findMember = memberService.findVerifiedMember(answer.getMember().getMemberId());
        Questions findQuestion = questionsRepository.findById(answer.getQuestion().getQuestionId())
                        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        answer.setMember(findMember);
        answer.setQuestion(findQuestion);

        findMember.getAnswers().add(answer);
        findQuestion.getAnswer().add(answer);

        return answerRepository.save(answer);

    }

    //채택 기능 구현
    //QuestionId를 불러와서 그 아래에 있는 답변들 확인.
    //해당 답변들 중에서 채택된 것이 있는지 확인하고 없다면 채택 가능.
    //있다면 채택 불가능.
    public boolean acceptedAnswer(long answerId, long questionId) {
        List<Answer> answersForQuestion = answerRepository.findByQuestionQuestionId(questionId);

        for(Answer answer : answersForQuestion) {
            if(answer.getAnswerId() == answerId) {
                answer.setAccepted(true);
                answerRepository.save(answer);
                return true;
            }
        }
        return false;
    }

    public Answer updateAnswer(Answer answer) {

        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> findAnswer.setBody(body));

        return answerRepository.save(findAnswer);
    }

    @Transactional(readOnly = true)
    public List<Answer> findAnswers(long questionId) {
        List<Answer> answers = answerRepository.findByQuestionQuestionId(questionId);

        return answers;
    }

    public Answer findAnswer(long questionId, long answerId) {

        return verifyExistAnswer(answerId);
    }


    public void deleteAnswer(Answer answer) {
        Answer findanswer = verifyExistAnswer(answer.getAnswerId());

        if(findanswer.getMember().getMemberId() != answer.getMember().getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        }
        else {
            answerRepository.delete(answer);
        }
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findAnswer;
    }

    private Answer verifyExistAnswer(long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return answer;
    }

}
