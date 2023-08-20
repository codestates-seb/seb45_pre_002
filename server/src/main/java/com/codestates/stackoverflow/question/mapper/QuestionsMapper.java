package com.codestates.stackoverflow.question.mapper;

import com.codestates.stackoverflow.question.dto.QuestionResponseDto;
import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.dto.QuestionsPostDto;
import com.codestates.stackoverflow.question.entity.Questions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionsMapper {
    Questions questionPatchToQuestion(QuestionsPatchDto questionsPatchDto);

    List<QuestionResponseDto> questionListToQuestionsResponse(List<Questions> questionsList);

    default QuestionResponseDto questionToQuestionsResponse(Questions questions) {
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

    default Questions questionsPostToQuestion (QuestionsPostDto questionsPostDto) {

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