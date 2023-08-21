package com.codestates.stackoverflow.question.service;


import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.repository.QuestionsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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


    public Questions createQuestion(Questions questions, Long memberId) {

        questions.setMember(memberService.findMember(memberId));
        verifyQuestion(questions);

        return saveQuestion(questions);
    }

    public Questions findQuestion(Long questionId) {
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

    public void updateQuestionsViewCount(Questions questions, Long viewCount) {

        questions.updateViewCount(viewCount + 1);
        saveQuestion(questions);
    }

    public Questions updateQuestions(Questions questions, Long memberId) {

        Questions foundQuestion = findQuestion(questions.getQuestionId());

        verifyUserAuthorization(memberId, foundQuestion.getMember().getMemberId());

        Optional.ofNullable(questions.getQuestionTitle())
                .ifPresent(foundQuestion::setQuestionTitle);
        Optional.ofNullable(questions.getQuestionBody())
                .ifPresent(foundQuestion::setQuestionBody);

        return saveQuestion(foundQuestion);
    }

    public void verifyUserAuthorization(Long authorizedUserId, Long tryingMemberId) {
        //member의 권한 확인

        if (!Objects.equals(authorizedUserId,tryingMemberId))
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED_USER);
    }


    public void  deleteQuestions(Long questionId, Long memberId) {

        Questions questions = findVerifiedQuestionById(questionId);
        verifyUserAuthorization(questions.getMember().getMemberId(), memberId);

        questionsRepository.delete(questions);
    }

   public void verifyQuestion(Questions questions) {

        memberService.findMember(questions.getMember().getMemberId());

    }

   public Questions findVerifiedQuestionById(Long questionId) {

        Optional<Questions> optionalQuestions = questionsRepository.findById(questionId);
        Questions foundQuestion = optionalQuestions.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return foundQuestion;
    }

    private Questions saveQuestion(Questions questions) {

        return questionsRepository.saveAndFlush(questions);
    }

    public Long getVoteCount(Long questionId) {

        Questions questions = findVerifiedQuestionById(questionId);
        return questions.getVoteCount();
    }

    //TODO: 투표 방식 다시 생각하기

    /*
    public void setUpVote(Long questionId, Long memberId ) {

        memberService.findMember(memberId);

        Questions questions = findVerifiedQuestionById(questionId);
        VoteStatus status = getUserVoteStatus(questions, memberId);
        Long voteCountNum = questions.getVoteCount();

        if(status == VoteStatus.NO_VOTE) {
            questions.upVotedUserId.add(memberId);
            voteCountNum++;
        }
        questions.updateVoteCount(voteCountNum);
    }

    public void setDownVote(Long questionId, Long memberId) {

        memberService.findMember(memberId);

        Questions questions = findVerifiedQuestionById(questionId);
        VoteStatus status = getUserVoteStatus(questions, memberId);
        Long voteCountNum = questions.getVoteCount();

        if(status == VoteStatus.NO_VOTE) {
            questions.upVotedUserId.add(memberId);
            voteCountNum++;
        }

        questions.updateVoteCount(voteCountNum);
    }

    private VoteStatus getUserVoteStatus (Questions questions, Long memberId) {

        if(questions.getUpVotedUserId().contains(memberId)) {
            return VoteStatus.DID_UP_VOTE;
        }else if(questions.getDownVotedUserId().contains(memberId)){
            return VoteStatus.DID_DOWN_VOTE;
        }else{
            return VoteStatus.NO_VOTE;
        }
    }

     */

    /*
    public enum VoteStatus{
        DID_UP_VOTE(1,"did up vote"),
        NO_VOTE(2,"did no vote"),
        DID_DOWN_VOTE(3,"did down vote");
    }

    @Getter
    private int status;

    @Getter
    private String message;

    VoteStatus(int code, String message) {
        this.status = code;
        this.message = message;
    }

     */
}
