package com.codestates.stackoverflow.question.mapper;

import com.codestates.stackoverflow.question.dto.QuestionResponseDto;
import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.dto.QuestionsPostDto;
import com.codestates.stackoverflow.question.entity.Questions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionsMapper {
    public Questions questionPatchToQuestion(QuestionsPatchDto questionsPatchDto) {

        Questions.QuestionsBuilder questionsBuilder = Questions.builder();

        if( questionsPatchDto.getQuestionTitle() != null ) {
            questionsBuilder.questionTitle(questionsPatchDto.getQuestionTitle());
        }

        if(questionsPatchDto.getQuestionBody() != null) {
            questionsBuilder.questionBody(questionsPatchDto.getQuestionBody());
        }

        return questionsBuilder.build();
    };

    public List<QuestionResponseDto> questionListToQuestionsResponse(List<Questions> questionsList) {

        if(questionsList == null) {
            return null;
        }

        List<QuestionResponseDto> responseDtoList = new ArrayList<>();

       for(Questions questions : questionsList) {

           QuestionResponseDto questionResponseDto = new QuestionResponseDto();
           questionResponseDto.setQuestionId(questions.getQuestionId());
           questionResponseDto.setQuestionTitle(questions.getQuestionTitle());
           questionResponseDto.setQuestionBody(questions.getQuestionBody());

           responseDtoList.add(questionResponseDto);
       }

        return responseDtoList;
    };

    public QuestionResponseDto questionToQuestionsResponse(Questions questions) {

        if(questions == null) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        questionResponseDto.setQuestionId(questions.getQuestionId());
        questionResponseDto.setMemberId(questions.getMember().getMemberId());
        questionResponseDto.setQuestionTitle(questions.getQuestionTitle());
        questionResponseDto.setQuestionBody(questions.getQuestionBody());

        if ( questions.getViewCount() != null) {
            questionResponseDto.setViewCount( questions.getViewCount().intValue());
        }

        if (questions.getVoteCount() != null) {
            questionResponseDto.setVoteCount( questions.getVoteCount().intValue());
        }

        questionResponseDto.setCreatedAt(questions.getCreatedAt());
        questionResponseDto.setModifiedAt(questions.getModifiedAt());

        return questionResponseDto;
    }

    public Questions questionsPostToQuestion (QuestionsPostDto questionsPostDto) {

        if( questionsPostDto == null) {
            return null;
        }

        Questions.QuestionsBuilder questionsBuilder = Questions.builder();

        questionsBuilder.questionTitle( questionsPostDto.getQuestionTitle());
        questionsBuilder.questionBody(questionsPostDto.getQuestionBody());

        Questions questions = questionsBuilder.build();

        return questions;
    }
}