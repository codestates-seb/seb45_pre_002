package com.codestates.stackoverflow.questionvote.controller;

import com.codestates.stackoverflow.questionvote.dto.QuestionVoteDto;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import com.codestates.stackoverflow.questionvote.mapper.QuestionVoteMapper;
import com.codestates.stackoverflow.questionvote.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question-votes")
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;

    private final QuestionVoteMapper questionVoteMapper;

    @PostMapping
    public ResponseEntity postQuestionVote(@RequestBody QuestionVoteDto.PostDto postDto) {
        QuestionVote questionVote = questionVoteMapper.postToQuestionVote(postDto);

        questionVote = questionVoteService.createQuestionVote(questionVote);

        QuestionVoteDto.ResponseDto responseDto = questionVoteMapper.questionVoteToResponse(questionVote);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
