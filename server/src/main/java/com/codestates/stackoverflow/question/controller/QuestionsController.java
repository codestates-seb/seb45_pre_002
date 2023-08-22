package com.codestates.stackoverflow.question.controller;

import com.codestates.stackoverflow.answercomment.dto.AnswerCommentDto;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.question.dto.QuestionsPageDto;
import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.dto.QuestionsPostDto;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.mapper.QuestionsMapper;
import com.codestates.stackoverflow.question.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
@Validated
@RequiredArgsConstructor
public class QuestionsController {
    private final QuestionsService questionsService;
    private final QuestionsMapper questionsMapper;

    //TODO: 이미지 삽입
    //TODO: 로그인 했을 때만 가능하게
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionsPostDto questionsPostDto) {

        //질문 생성
        Questions questions = questionsService.createQuestion(questionsMapper.questionsPostToQuestion(questionsPostDto),
                questionsPostDto.getMemberId());


        return new ResponseEntity<>(questionsMapper.questionToQuestionsResponse(questions), HttpStatus.CREATED);
    }

    //TODO: 수정 날짜와 시간 표시
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionsPatchDto questionsPatchDto) {

        questionsPatchDto.setQuestionId(questionId);
        Questions questions = questionsService.updateQuestions(questionsMapper.questionPatchToQuestion(questionsPatchDto),
                questionsPatchDto.getMemberId());


        return new ResponseEntity<>(questionsMapper.questionToQuestionsResponse(questions),HttpStatus.OK);

    }

    // 하나의 게시글만 조회
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion (@PathVariable("question-id") @Positive long questionId){

        Questions questions = questionsService.findQuestion(questionId);
        questionsService.updateQuestionsViewCount(questions,questions.getViewCount());

        return new ResponseEntity<>(questionsMapper.questionToQuestionsResponse(questions), HttpStatus.OK);
    }

    // 전체 질문 조회
    /*
    @GetMapping
    public ResponseEntity getQuestions() {

        List<Questions> questionsList = questionsService.getAllquestions();

        return new ResponseEntity<>(questionsMapper.questionListToQuestionsResponse(questionsList), HttpStatus.OK);

    }

     */

    @DeleteMapping (value = "/{question-id}")
    public ResponseEntity deleteQuestions (@PathVariable("question-id") @Positive long questionId,
                                           @Positive @RequestParam long memberId) {
        questionsService.deleteQuestions(questionId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/pageNumber={pageNumber}/pageSize={pageSize}")
    public ResponseEntity findquestions(@PathVariable ("pageNumber") @Positive int pageNumber,
                                        @PathVariable ("pageSize") @Positive int pageSize) {
        Page<Questions> question = questionsService.findquestions(pageNumber, pageSize);

        QuestionsPageDto.PageResponseDto pageResponseDto = questionsMapper.questionsPageToPageResponseDto(question);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }
}
