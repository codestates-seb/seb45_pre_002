package com.codestates.stackoverflow.answervote.service;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answer.service.AnswerService;
import com.codestates.stackoverflow.answervote.entity.AnswerVote;
import com.codestates.stackoverflow.answervote.repository.AnswerVoteRepository;
import com.codestates.stackoverflow.exception.BusinessLogicException;
import com.codestates.stackoverflow.exception.ExceptionCode;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository answerVoteRepository;

    private final AnswerService answerService;

    public AnswerVote createAnswerVote(AnswerVote answerVote) {
        //존재하는 답변인지 확인 후 해당 답변을 가져옴
        Answer answer = answerService.findVerifiedAnswer(answerVote.getAnswer().getAnswerId());

        //같은 멤버가 같은 답변글에 중복투표 했을 경우
        if(answer.getAnswerVotes().stream()
                .anyMatch(value -> value.getMember().getMemberId() == answerVote.getMember().getMemberId())) {
            //가져온 답변의 투표 목록에서 투표하려는 멤버의 id와 같은 객체를 찾아 List 로 리턴(List.size() = 1)
            List<AnswerVote> answerVoteList = answer.getAnswerVotes().stream()
                    .filter(vote -> vote.getMember().getMemberId() == answerVote.getMember().getMemberId()).collect(Collectors.toList());
            //답변의 투표목록에서 가져온 투표 객체의 id를 이용해 투표DB 에서 이전에 투표했던 기록을 가져옴
            AnswerVote findAnswerVote = answerVoteRepository.findById(answerVoteList.get(0).getAnswerVoteId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
            //이전에 투표했던것과 같은 옵션을 골랐을 경우
            if(!findAnswerVote.isLike() ^ answerVote.isLike()) {
                throw new BusinessLogicException(ExceptionCode.ANSWER_VOTE_EXISTS);
            }

            //다른 옵션을 골랐을 경우
            // 해당 객체를 Answer 의 AsnwerVote List 에서 삭제
            answer.getAnswerVotes().remove(findAnswerVote);
            // 해당 객체를 DB 에서 삭제
            answerVoteRepository.delete(findAnswerVote);
            return null;
        }

        AnswerVote savedAnswerVote = answerVoteRepository.save(answerVote);

        answer.getAnswerVotes().add(savedAnswerVote);

        return savedAnswerVote;
    }

    public long getTotalVoteCount(long questionId, long answerId) {
        Answer answer = answerService.findVerifiedAnswer(answerId);

        long totalVoteCount = answer.getAnswerVotes().size();

        return totalVoteCount;
    }

    public AnswerVote verifyExistQuestionVote(long answerVoteId) {
        AnswerVote answerVote = answerVoteRepository.findById(answerVoteId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return answerVote;
    }
}
