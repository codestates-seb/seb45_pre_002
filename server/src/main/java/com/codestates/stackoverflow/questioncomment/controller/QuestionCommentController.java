package com.codestates.stackoverflow.questioncomment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question-comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QuestionCommentController {

    @PostMapping
    public ResponseEntity postQuestionComment() {


        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
