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
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/answers")
@RequiredArgsConstructor
@Validated
public class AnswerController {

    private final static String USER_DEFAULT_URL = "/answers";

    private final AnswerMapper mapper;

    private final AnswerService service;

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerDto.PostDto postDto) {

        memberService.findMember(postDto.getMemberId());

        Answer answer = service.createAnswer(mapper.postToAnswer(postDto));

        URI location = UriCreator.createUri(USER_DEFAULT_URL, answer.getAnswerId());

        return new ResponseEntity<>(location, HttpStatus.OK);
    }

}
