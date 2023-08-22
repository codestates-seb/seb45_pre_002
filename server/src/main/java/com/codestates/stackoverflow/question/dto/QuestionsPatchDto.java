package com.codestates.stackoverflow.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionsPatchDto {

    @NotNull
    private long memberId;

    private long questionId;

    @NotBlank
    private String questionTitle;

    @NotBlank
    private String questionBody;

}
