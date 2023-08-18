package com.codestates.stackoverflow.question.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class QuestionsPatchDto {

    @NotNull
    private Long userId;


    private Long questionId;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionBody;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    //TODO : 제목, 질문 글자 수 정하기?
}
