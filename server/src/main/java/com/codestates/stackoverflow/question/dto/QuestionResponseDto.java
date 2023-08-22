package com.codestates.stackoverflow.question.dto;

import com.codestates.stackoverflow.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {

    private long question_id;
    private long member_id;
    private String title;
    private String body;
    private long view_count;
    private String created_at;
    private String last_modified_at;

}
