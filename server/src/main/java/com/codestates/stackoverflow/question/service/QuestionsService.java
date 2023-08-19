package com.codestates.stackoverflow.question.service;


import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final MemberService memberService;

    public QuestionsService(QuestionsRepository questionsRepository, MemberService memberService) {
        this.questionsRepository =questionsRepository;
        this.memberService = memberService;
    }


  //  public Questions creqteQuestion (Questions questions, Long userId) {


  //  }

   // public Questions findQuestion(Long questionId) {
    //    return findVerifiedQuestionBtId(questionId);

  //  }

  //  public List<Questions> questions() {

      //  List<Questions> questionsList = questionsRepository.findAllByOrderByCreatedDesc()
       //         .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

       // return questionsList;

  //  }

 //   public List<Questions> searchQuestionsByTitle(String questionTitle) {
/*
        Optional<List<Questions>> questionsList = questionsRepository.findByQuestionsTiltleContaining(questionTitle);
        List<Questions> foundQuestionList = questionsList.orElseThrow(() -> new BusinessLogicException((ExceptionCode.QUESTION_NOT_FOUND)));

        return foundQuestionList;


 */
     //   return null;
   // }

  //  public void updateQuestionsViewCount(Questions questions, Long viewCount) {
/*
        questions.updateViewCount(viewCount + 1);
        saveQuestion(questions);

        Member member = MemberService.class.getClass(questions.getMember().getMemberId());
        member.updateUserViewCounts(member.getUserViewCounts() +1);
        memberService.saveMember(member);

 */
  //  }
/*
    public Questions updateQuestions(Questions questions, Long memberId) {

        Questions foundQuestion = findQuestion(questions.getQuestionId());

        memberService.findVerifiedMember(memberId, foundQuestion.getMember().getMemberId());

        Optional.ofNullable(questions.getQuestionTitle())
                .ifPresent(foundQuestion::setQuestionTitle);
        Optional.ofNullable(questions.getQuestionBody())
                .ifPresent(foundQuestion::setQuestionBody);

        return saveQuesion(foundQuestion);
    }

    public deleteQuestions(Long questionId, Long memberId) {

        Questions questions = findVerifiedQuesiondById(questionId);
        memberService.verifyUserAuthorization(questions.getMember().getMemberId(), memberId);

        questionsRepository.delete(questions);
    }

    private void verifyQuestion(Questions questions) {

        memberService.findMember(questions.getMember().getMemberId());

    }
    */
}

