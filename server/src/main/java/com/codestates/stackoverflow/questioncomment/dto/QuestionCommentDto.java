package com.codestates.stackoverflow.questioncomment.dto;

import com.codestates.stackoverflow.utils.PageInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class QuestionCommentDto {

    @Getter
    @Setter
    public static class PostDto {

        @NotBlank(message = "본문은 비어있거나 공백으로만 이루어져 있지 않아야 합니다.")
        private String body;

        @Positive
        private long member_id;

        @Positive
        private long question_id;
    }

    @Getter
    @Setter
    public static class PatchDto {

        @NotBlank(message = "본문은 비어있거나 공백으로만 이루어져 있지 않아야 합니다.")
        private String body;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private long question_comment_id;

        private String body;

        private String created_at;

        private String last_modified_at;
    }

    @Getter
    @Setter
    public static class PageResponseDto<T> {

        private T data;

        private PageInfo pageInfo;
    }
}