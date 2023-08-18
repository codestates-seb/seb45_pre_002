package com.codestates.stackoverflow.questioncomment.controller;

import com.codestates.stackoverflow.questioncomment.dto.QuestionCommentDto;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.questioncomment.mapper.QuestionCommentMapper;
import com.codestates.stackoverflow.questioncomment.service.QuestionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question-comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class QuestionCommentController {

    private final QuestionCommentMapper questionCommentMapper;
    private final QuestionCommentService questionCommentService;

    @PostMapping
    public ResponseEntity postQuestionComment(@Valid @RequestBody QuestionCommentDto.PostDto postDto) {
        QuestionComment questionComment = questionCommentMapper.postToQuestionComment(postDto);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
