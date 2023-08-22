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
    private long member_id;

    private long question_id;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

}
