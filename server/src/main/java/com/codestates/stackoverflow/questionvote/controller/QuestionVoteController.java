package com.codestates.stackoverflow.questionvote.controller;

import com.codestates.stackoverflow.answervote.dto.AnswerVoteDto;
import com.codestates.stackoverflow.questionvote.dto.QuestionVoteDto;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import com.codestates.stackoverflow.questionvote.mapper.QuestionVoteMapper;
import com.codestates.stackoverflow.questionvote.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;

    private final QuestionVoteMapper questionVoteMapper;

    @PostMapping("/question-votes")
    public ResponseEntity postQuestionVote(@RequestBody QuestionVoteDto.PostDto postDto) {
        QuestionVote questionVote = questionVoteMapper.postToQuestionVote(postDto);

        questionVote = questionVoteService.createQuestionVote(questionVote);

        QuestionVoteDto.ResponseDto responseDto = questionVoteMapper.questionVoteToResponse(questionVote);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/questions/{question-id}/votes")
    public ResponseEntity getAnswerVotes(@PathVariable("question-id") @Positive long questionId) {
        long totalVoteCount = questionVoteService.getTotalVoteCount(questionId);

        QuestionVoteDto.TotalVoteCountResponseDto responseDto = questionVoteMapper.questionVoteTotalVoteCountResponse(totalVoteCount);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
