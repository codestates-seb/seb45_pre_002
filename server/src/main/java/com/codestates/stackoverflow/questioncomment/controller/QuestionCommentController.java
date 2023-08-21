package com.codestates.stackoverflow.questioncomment.controller;

import com.codestates.stackoverflow.questioncomment.dto.QuestionCommentDto;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.questioncomment.mapper.QuestionCommentMapper;
import com.codestates.stackoverflow.questioncomment.service.QuestionCommentService;
import com.codestates.stackoverflow.questionvote.mapper.QuestionVoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

        questionComment = questionCommentService.createQuestionComment(questionComment);

        QuestionCommentDto.ResponseDto responseDto = questionCommentMapper.questionCommentToResponse(questionComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{question-comment-id}")
    public ResponseEntity patchQuestionComment(@PathVariable("question-comment-id") @Positive long questionCommentId,
                                               @Valid @RequestBody QuestionCommentDto.PatchDto patchDto) {
        QuestionComment questionComment = questionCommentMapper.patchToQuestionComment(patchDto);
        questionComment.setQuestionCommentId(questionCommentId);

        questionComment = questionCommentService.updateQuestionComment(questionComment);

        QuestionCommentDto.ResponseDto responseDto = questionCommentMapper.questionCommentToResponse(questionComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{question-comment-id}")
    public ResponseEntity findQuestionComment(@PathVariable("question-comment-id") @Positive long questionCommentId) {
        QuestionComment questionComment = questionCommentService.findQuestionComment(questionCommentId);

        QuestionCommentDto.ResponseDto responseDto = questionCommentMapper.questionCommentToResponse(questionComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findQuestionComments(@RequestParam @Positive int pageNumber,
                                               @RequestParam @Positive int pageSize) {
        Page<QuestionComment> questionComment = questionCommentService.findQuestionComments(pageNumber, pageSize);

        QuestionCommentDto.PageResponseDto pageResponseDto = questionCommentMapper.questionCommentPageToPageResponseDto(questionComment);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteQuestionComment(@Valid @RequestBody QuestionCommentDto.DeleteDto deleteDto) {
        QuestionComment questionComment = questionCommentMapper.deleteToQuestionComment(deleteDto);

        questionCommentService.deleteQuestionComment(questionComment);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}