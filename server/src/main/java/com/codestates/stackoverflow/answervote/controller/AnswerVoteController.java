package com.codestates.stackoverflow.answervote.controller;

import com.codestates.stackoverflow.answervote.dto.AnswerVoteDto;
import com.codestates.stackoverflow.answervote.entity.AnswerVote;
import com.codestates.stackoverflow.answervote.mapper.AnswerVoteMapper;
import com.codestates.stackoverflow.answervote.service.AnswerVoteService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer-votes")
@CrossOrigin(value = "*")
@RequiredArgsConstructor
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;

    private final AnswerVoteMapper answerVoteMapper;

    @PostMapping
    public ResponseEntity postAnswerVote(@RequestBody AnswerVoteDto.PostDto postDto) {
        AnswerVote answerVote = answerVoteMapper.postToAnswerVote(postDto);

        answerVote = answerVoteService.createAnswerVote(answerVote);

        AnswerVoteDto.ResponseDto responseDto = answerVoteMapper.answerVoteToResponse(answerVote);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
