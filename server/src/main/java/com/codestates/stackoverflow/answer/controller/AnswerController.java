package com.codestates.stackoverflow.answer.controller;


import com.codestates.stackoverflow.answer.dto.AnswerDto;
import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates.stackoverflow.answer.service.AnswerService;
import com.codestates.stackoverflow.member.service.MemberService;
import com.codestates.stackoverflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping
@RequiredArgsConstructor
@Validated
public class AnswerController {

    private final static String USER_DEFAULT_URL = "/answers";

    private final AnswerMapper mapper;

    private final AnswerService service;

    private final MemberService memberService;

    @PostMapping("/answers")
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.PostDto postDto) {

        memberService.findMember(postDto.getMember_id());

        Answer answer = service.createAnswer(mapper.postToAnswer(postDto));

        URI location = UriCreator.createUri(USER_DEFAULT_URL, answer.getAnswerId());

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping("/questions/{question-id}/answers/{answer-id}")
    public ResponseEntity acceptAnswer(@PathVariable("answer-id") @Positive long answerId,
                                       @PathVariable("question-id") @Positive long questionId) {

        boolean success = service.acceptedAnswer(questionId, answerId);

        if(success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/answers/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerDto.PatchDto patchDto){

        Answer answer = mapper.patchToAnswer(patchDto);

        answer.setAnswerId(answerId);

        Answer response = service.updateAnswer(answer);

        return new ResponseEntity<>(mapper.answerToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/questions/{question-id}/answers/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("question-id") @Positive long questionId,
                                    @PathVariable("answer-id") @Positive long answerId) {

        Answer answer = service.findAnswer(questionId, answerId);

        return new ResponseEntity<>(mapper.answerToResponse(answer), HttpStatus.OK);
    }

    @GetMapping("/questions/{question-id}/answers")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive long questionId) {
        List<Answer> answer = service.findAnswers(questionId);

        return new ResponseEntity<>(mapper.answersToListResponseDto(answer), HttpStatus.OK);
    }

    @DeleteMapping("/questions/{question-id}/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@Valid @RequestBody AnswerDto.DeleteDto deleteDto) {

        Answer answer = mapper.deleteToAnswer(deleteDto);

        service.deleteAnswer(answer);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }

}
