package com.codestates.stackoverflow.answercomment.controller;

import com.codestates.stackoverflow.answercomment.dto.AnswerCommentDto;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.answercomment.mapper.AnswerCommentMapper;
import com.codestates.stackoverflow.answercomment.service.AnswerCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/answer-comments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnswerCommentController {

    private final AnswerCommentMapper answerCommentMapper;

    private final AnswerCommentService answerCommentService;

    @PostMapping
    public ResponseEntity postAnswerComment(@Valid @RequestBody AnswerCommentDto.PostDto postDto) {
        AnswerComment answerComment = answerCommentMapper.postToAnswerComment(postDto);

        answerComment = answerCommentService.createAnswerComment(answerComment);

        AnswerCommentDto.ResponseDto responseDto = answerCommentMapper.answerCommentToResponse(answerComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{answer-comment-id}")
    public ResponseEntity patchAnswerComment(@PathVariable("answer-comment-id") @Positive long answerCommentId,
                                             @Valid @RequestBody AnswerCommentDto.PatchDto patchDto) {
        AnswerComment answerComment = answerCommentMapper.patchToAnswerComment(patchDto);
        answerComment.setAnswerCommentId(answerCommentId);

        answerComment = answerCommentService.updateAnswerComment(answerComment);

        AnswerCommentDto.ResponseDto responseDto = answerCommentMapper.answerCommentToResponse(answerComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{answer-comment-id}")
    public ResponseEntity findAnswerComment(@PathVariable("answer-comment-id") @Positive long answerCommentId) {
        AnswerComment answerComment = answerCommentService.findAnswerComment(answerCommentId);
        AnswerCommentDto.ResponseDto responseDto = answerCommentMapper.answerCommentToResponse(answerComment);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findAnswerComments(@RequestParam @Positive int pageNumber,
                                             @RequestParam @Positive int pageSize) {
        Page<AnswerComment> answerComments = answerCommentService.findAnswerComments(pageNumber, pageSize);

        AnswerCommentDto.PageResponseDto pageResponseDto = answerCommentMapper.answerCommentPageToPageResponseDto(answerComments);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAnswerComment(@Valid @RequestBody AnswerCommentDto.DeleteDto deleteDto) {
        AnswerComment answerComment = answerCommentMapper.deleteToAnswerComment(deleteDto);

        answerCommentService.deleteAnswerComment(answerComment);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
