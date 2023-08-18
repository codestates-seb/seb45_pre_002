package com.codestates.stackoverflow.question.service;


import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final MemberService memberService;

    public QuestionsService(QuestionsRepository questionsRepository, MemberService memberService) {
        this.questionsRepository =questionsRepository;
        this.memberService = memberService;
    }


    public Questions creqteQuestion (Questions questions, Long userId) {

        return null;
    }

    public Questions findQuestion(Long questionId) {
        return null;
    }
}
