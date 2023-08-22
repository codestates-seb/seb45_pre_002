package com.codestates.stackoverflow.question.dto;

import com.codestates.stackoverflow.answer.dto.AnswerDto;
import com.codestates.stackoverflow.utils.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class QuestionsPageDto {
    @Getter
    @Setter
    public static class PageResponseDto {

        //private T questions;
        private List<QuestionResponseDto> questions;
        private PageInfo pageInfo;
    }
}
