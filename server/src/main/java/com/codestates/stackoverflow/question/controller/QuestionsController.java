package com.codestates.stackoverflow.question.controller;

import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.question.mapper.QuestionsMapper;
import com.codestates.stackoverflow.question.service.QuestionsService;
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
public class QuestionsController {
    private QuestionsService questionsService;
    private QuestionsMapper questionsMapper;

    public QuestionsController(QuestionsMapper questionsMapper, QuestionsService questionsService) {
        this.questionsMapper = questionsMapper;
        this.questionsService = questionsService;
    }

    //TODO: 이미지 삽입
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionsPatchDto questionsDto) {
/*
        Questions questions = questionsService.createQuestion(questionsMapper.questionPostDtoToQuestion(questionPostDto),
                questionPostDto.getUserId());

        return new ResponseEntity<>(
                new ResponseDto<>(questionsMapper.questionToQuestionsSimpleResponseDto(question), HttpStatus.CREATED);
       )

 */
        return null;
    }

    //TODO: 수정 날짜와 시간 표시
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive Long questionsId,
                                        @Valid @RequestBody QuestionsPatchDto questionsPatchDto) {

      //  questionsPatchDto.setQuestionsId(questionsId);
      //  Questions questions = questionsService.updateQuestions(questionsMapper.questionsPatchDtoToQuesions(questionsPatchDto),
         //       questionsPatchDto.getUserId());

      //  return new ResponseEntity<>(
       //         new ResponseDto<>(questionsMapper.questionToQuestionsSimpleResponseDto(questions),HttpStatus.OK);
      //  )

        return null;
    }

    // 하나의 게시글만 조회
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion (@PathVariable("question-id") @Positive Long questionsId,
                                       HttpServletRequest req, HttpServletResponse res){

      //  Questions questions = questionsService.findQuestions(questionsId);
      //  questionsService.updateQuestionsViewCount(questions, questions.getViewCount());

        javax.servlet.http.Cookie[] cookies = req.getCookies();
        Map<String, String> mapCookies = new HashMap<String, String>();

        if (req.getCookies() != null) {
            for(javax.servlet.http.Cookie value : cookies) {
                mapCookies.put(value.getName(), value.getValue());
            }
        }

        String viewCount = (mapCookies.get("view_count"));

       // questionsService.updateQuestionsViewCount (questions, questions,getViewCount());

     //   return new ResponseEntity<>(
           //    new ResponseDto(questionsMapper.questionsToQuestionsSimpleResponseDto(questions), HttpStatus.OK);

      //  )

        return null;
    }

    // 전체 질문 조회
    @GetMapping()
    public ResponseEntity getQuestions() {

      // List<Questions> questionsList = questionsService.getQuestions();

      //  return new ResponseEntity<>(questionsMapper.questionsListToQuestionsSimpleResponseDto(questionsList), HttpStatus.OK);

        return null;
    }

    @DeleteMapping (value = "/{question-id}")
    public ResponseEntity deleteQuestions (@PathVariable("question-id") @Positive Long questionId,
                                           @Positive @RequestParam Long userId) {
      //  questionsService.deleteQuestions(questionId, userId);

      //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return null;
    }

    /*

    //TODO : VOTEDTO 만들기 -> REQUESTBODY로 받아오기!
    //투표수 up
    @PostMapping("/question-votes")
    public ResponseEntity setUpVote(@PathVariable("question-id") @Positive Long questionId,
                                  @Positive @RequestParam Long userId) {
        questionsService.setUpVote(questionId, userId);

        return new ResponseEntity(new ResponseDto<>(questionsService.getVoteCount(questionId)), HttpStatus.OK);

    }

    //투표수 down
    @PostMapping("/question-votes")
    public ResponseEntity setDownVote(@PathVariable("question-id") @Positive Long questionId,
                                      @Positive @RequestParam Long userId) {

        questionsService.setDownVote(questionId, userId);

        return new ResponseEntity(new ResponseDto<> (questionsService.getVoteCount(questionId)), HttpStatus.OK);

    }

     */
}
