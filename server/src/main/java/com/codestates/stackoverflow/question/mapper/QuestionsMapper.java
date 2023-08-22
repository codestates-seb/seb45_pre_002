package com.codestates.stackoverflow.question.mapper;

import com.codestates.stackoverflow.answercomment.dto.AnswerCommentDto;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.dto.QuestionResponseDto;
import com.codestates.stackoverflow.question.dto.QuestionsPageDto;
import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.dto.QuestionsPostDto;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.utils.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionsMapper {
    public Questions questionPatchToQuestion(QuestionsPatchDto questionsPatchDto) {

        Questions questions = new Questions();

        questions.setQuestionTitle(questionsPatchDto.getQuestionTitle());
        questions.setQuestionBody(questionsPatchDto.getQuestionBody());
        questions.setQuestionId(questionsPatchDto.getQuestionId());

        return questions;
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
        questionResponseDto.setViewCount(questions.getViewCount());
        questionResponseDto.setCreatedAt(questions.getCreatedAt());
        questionResponseDto.setLastModifiedAt(questions.getLastModifiedAt());

        return questionResponseDto;
    }

    public Questions questionsPostToQuestion (QuestionsPostDto questionsPostDto) {

        if( questionsPostDto == null) {
            return null;
        }
        Questions questions = new Questions();

        questions.setQuestionTitle(questionsPostDto.getQuestionTitle());
        questions.setQuestionBody(questionsPostDto.getQuestionBody());

        return questions;
    }

    public QuestionsPageDto.PageResponseDto questionsPageToPageResponseDto(Page<Questions> question) {

        if(question == null) {
            return null;
        }
        else {
            QuestionsPageDto.PageResponseDto pageResponseDto = new QuestionsPageDto.PageResponseDto();

            pageResponseDto.setQuestions(question.getContent());
            pageResponseDto.setPageInfo(PageInfo.builder()
                    .pageNumber(question.getNumber())
                    .pageSize(question.getSize())
                    .totalElements(question.getTotalElements())
                    .totalPages(question.getTotalPages())
                    .build());

            return pageResponseDto;
        }
    }
}