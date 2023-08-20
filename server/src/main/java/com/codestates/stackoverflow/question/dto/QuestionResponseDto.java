package com.codestates.stackoverflow.question.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {

    private Long questionId;
    private Long memberId;
    private String questionTitle;
    private String questionBody;
    private int viewCount;
    private int voteCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}