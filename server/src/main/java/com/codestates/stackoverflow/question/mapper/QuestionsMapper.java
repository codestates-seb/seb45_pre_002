package com.codestates.stackoverflow.question.mapper;

import com.codestates.stackoverflow.question.dto.QuestionResponseDto;
import com.codestates.stackoverflow.question.dto.QuestionsPageDto;
import com.codestates.stackoverflow.question.dto.QuestionsPatchDto;
import com.codestates.stackoverflow.question.dto.QuestionsPostDto;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.utils.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionsMapper {
    public Questions questionPatchToQuestion(QuestionsPatchDto questionsPatchDto) {

        Questions questions = new Questions();

        questions.setQuestionTitle(questionsPatchDto.getTitle());
        questions.setQuestionBody(questionsPatchDto.getBody());
        questions.setQuestionId(questionsPatchDto.getQuestion_id());

        return questions;
    };

    public QuestionResponseDto questionToQuestionsResponse(Questions questions) {

        if(questions == null) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        questionResponseDto.setQuestion_id(questions.getQuestionId());
        questionResponseDto.setMember_id(questions.getMember().getMemberId());
        questionResponseDto.setTitle(questions.getQuestionTitle());
        questionResponseDto.setBody(questions.getQuestionBody());
        questionResponseDto.setView_count(questions.getViewCount());
        questionResponseDto.setCreated_at(questions.getCreatedAt());
        questionResponseDto.setLast_modified_at(questions.getLastModifiedAt());

        return questionResponseDto;
    }

    public Questions questionsPostToQuestion (QuestionsPostDto questionsPostDto) {

        if( questionsPostDto == null) {
            return null;
        }
        Questions questions = new Questions();

        questions.setQuestionTitle(questionsPostDto.getTitle());
        questions.setQuestionBody(questionsPostDto.getBody());

        return questions;
    }

    public List<QuestionResponseDto> questionListToQuestionsResponse(List<Questions> questionsList) {

        if(questionsList == null) {
            return null;
        }

        List<QuestionResponseDto> responseDtoList = new ArrayList<>();

        for(Questions questions : questionsList) {

            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            questionResponseDto.setQuestion_id(questions.getQuestionId());
            questionResponseDto.setTitle(questions.getQuestionTitle());
            questionResponseDto.setBody(questions.getQuestionBody());

            responseDtoList.add(questionResponseDto);
        }

        return responseDtoList;
    };

    public QuestionsPageDto.PageResponseDto questionsPageToPageResponseDto(Page<Questions> questions) {

        if(questions == null) {
            return null;
        }
        else {
            QuestionsPageDto.PageResponseDto pageResponseDto = new QuestionsPageDto.PageResponseDto();

            List<QuestionResponseDto> pageResponseDtos = questions
                    .stream()
                            .map(question -> questionToQuestionsResponse(question))
                                    .collect(Collectors.toList());

            pageResponseDto.setQuestions(pageResponseDtos);
            pageResponseDto.setPageInfo(PageInfo.builder()
                    .pageNumber(questions.getNumber() + 1)
                    .pageSize(questions.getSize())
                    .totalElements(questions.getTotalElements())
                    .totalPages(questions.getTotalPages())
                    .build());

            return pageResponseDto;
        }
    }
}