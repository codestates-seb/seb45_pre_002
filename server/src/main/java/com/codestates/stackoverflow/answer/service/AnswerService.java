package com.codestates.stackoverflow.answer.service;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates.stackoverflow.answer.repository.AnswerRepository;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final MemberService memberService;

//    private final QuestionService questionService;

    private final AnswerMapper answerMapper;

    public Answer createAnswer(Answer answer) {

        verifyExistAnswer(answer);

        return answerRepository.save(answer);

    }

    private void verifyExistAnswer(Answer answer) {
        // member가 존재하는지 확인
        Member member = memberService.findMember(answer.getMember().getMemberId());
        answer.setMember(member);

        // 질문이 존재하는지 확인
//        Question question = questionService.findQuestion(answer.getQuestion().getQuestionId());
//        answer.setQuestion(question);

    }
}
