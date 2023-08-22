package com.codestates.stackoverflow.question.service;


import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.question.dto.QuestionsPageDto;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.mapper.QuestionsMapper;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final MemberService memberService;

    public QuestionsService(QuestionsRepository questionsRepository, MemberService memberService) {
        this.questionsRepository = questionsRepository;
        this.memberService = memberService;
    }

    public Page<Questions> findquestions(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("questionId").ascending());
        Page<Questions> question = questionsRepository.findAll(pageable);

        return question;
    }


    public Questions createQuestion(Questions questions, long memberId) {

        questions.setMember(memberService.findMember(memberId));
        verifyQuestion(questions);

        return saveQuestion(questions);
    }

    public Questions findQuestion(long questionId) {
        return findVerifiedQuestionById(questionId);

    }

    public List<Questions> getAllquestions() {

        List<Questions> questionsList = questionsRepository.findAllByOrderByCreatedAtDesc()
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return questionsList;

    }

    public List<Questions> searchQuestionsByTitle(String questionTitle) {

        Optional<List<Questions>> questionsList = questionsRepository.findByQuestionTitleContaining(questionTitle);
        List<Questions> foundQuestionList = questionsList.orElseThrow(() -> new BusinessLogicException((ExceptionCode.QUESTION_NOT_FOUND)));

        return foundQuestionList;

    }

    public void updateQuestionsViewCount(Questions questions, long viewCount) {

        questions.updateViewCount(viewCount + 1);
        saveQuestion(questions);
    }

    public Questions updateQuestions(Questions questions, long memberId) {

        Questions foundQuestion = findQuestion(questions.getQuestionId());

        verifyUserAuthorization(memberId, foundQuestion.getMember().getMemberId());

        Optional.ofNullable(questions.getQuestionTitle())
                .ifPresent(foundQuestion::setQuestionTitle);
        Optional.ofNullable(questions.getQuestionBody())
                .ifPresent(foundQuestion::setQuestionBody);

        return saveQuestion(foundQuestion);
    }

    public void verifyUserAuthorization(long authorizedUserId, long tryingMemberId) {
        //member의 권한 확인

        if (authorizedUserId != tryingMemberId)
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
    }


    public void  deleteQuestions(long questionId, long memberId) {

        Questions questions = findVerifiedQuestionById(questionId);
        verifyUserAuthorization(questions.getMember().getMemberId(), memberId);

        questionsRepository.delete(questions);
    }

   public void verifyQuestion(Questions questions) {

        memberService.findMember(questions.getMember().getMemberId());

    }

   public Questions findVerifiedQuestionById(long questionId) {

        Optional<Questions> optionalQuestions = questionsRepository.findById(questionId);
        Questions foundQuestion = optionalQuestions.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return foundQuestion;
    }

    private Questions saveQuestion(Questions questions) {

        return questionsRepository.save(questions);
    }

    public long getVoteCount(long questionId) {

        Questions questions = findVerifiedQuestionById(questionId);
        return questions.getVoteCount();
    }

}
