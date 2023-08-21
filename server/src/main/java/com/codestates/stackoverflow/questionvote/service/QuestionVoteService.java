package com.codestates.stackoverflow.questionvote.service;

import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.service.QuestionsService;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import com.codestates.stackoverflow.questionvote.repository.QuestionVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;

    private final QuestionsService questionsService;

    public QuestionVote createQuestionVote(QuestionVote questionVote) {
        //존재하는 질문인지 확인 후 해당 질문을 가져옴
        Questions question = questionsService.findVerifiedQuestionById(questionVote.getQuestion().getQuestionId());

        //같은 멤버가 같은 질문글에 중복투표 했을 경우
        if(question.getQuestionVotes().stream()
                .anyMatch(value -> value.getMember().getMemberId() == questionVote.getMember().getMemberId())) {
            //가져온 질문의 투표 목록에서 투표하려는 멤버의 id와 같은 객체를 찾아 List 로 리턴(List.size() = 1)
            List<QuestionVote> questionVoteList = question.getQuestionVotes().stream()
                    .filter(vote -> vote.getMember().getMemberId() == questionVote.getMember().getMemberId()).collect(Collectors.toList());
            //질문의 투표목록에서 가져온 투표 객체의 id를 이용해 투표DB 에서 이전에 투표했던 기록을 가져옴
            QuestionVote findQuestionVote = questionVoteRepository.findById(questionVoteList.get(0).getQuestionVoteId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_VOTE_NOT_FOUND));
            //이전에 투표했던것과 같은 옵션을 골랐을 경우
            if(!findQuestionVote.isLike() ^ questionVote.isLike()) {
                throw new BusinessLogicException(ExceptionCode.QUESTION_VOTE_EXISTS);
            }

            //다른 옵션을 골랐을 경우
            // 해당 객체를 Question 의 QuestionVote List 에서 삭제
            question.getQuestionVotes().remove(findQuestionVote);
            // 해당 객체를 DB 에서 삭제
            questionVoteRepository.delete(findQuestionVote);
            return null;
        }

        QuestionVote savedQuestionVote = questionVoteRepository.save(questionVote);

        question.getQuestionVotes().add(savedQuestionVote);

        return savedQuestionVote;
    }

    public QuestionVote verifyExistQuestionVote(long questionVoteId) {
        QuestionVote questionVote = questionVoteRepository.findById(questionVoteId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return questionVote;
    }
}
