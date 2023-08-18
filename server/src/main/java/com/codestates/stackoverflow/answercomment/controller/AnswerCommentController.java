package com.codestates.stackoverflow.answercomment.controller;

import com.codestates.stackoverflow.answercomment.dto.AnswerCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnswerCommentController {

    @PostMapping
    public ResponseEntity postAnswerComment(@Valid @RequestBody AnswerCommentDto.PostDto postDto) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
