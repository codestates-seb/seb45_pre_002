package com.codestates.stackoverflow.question.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class QuestionsPostDto {

    @NotNull
    private long memberId;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionBody;

}
