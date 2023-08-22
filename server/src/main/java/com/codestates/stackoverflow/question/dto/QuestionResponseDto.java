package com.codestates.stackoverflow.question.dto;

import com.codestates.stackoverflow.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {

    private long questionId;
    private long memberId;
    private String questionTitle;
    private String questionBody;
    private long viewCount;
    private long voteCount;
    private String createdAt;
    private String lastModifiedAt;

}
