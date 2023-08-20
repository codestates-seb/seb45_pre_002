package com.codestates.stackoverflow.answer.dto;

import com.codestates.stackoverflow.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class AnswerDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDto {

        private long memberId;

        @NotBlank(message = "내용을 입력해 주세요.")
        private String Body;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatchDto {

        private long memberId;

        @NotBlank(message = "내용을 입력해 주세요.")
        private String Body;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {

        private long answerId;

        private long memberId;

        private long questionId;

        private boolean accepted;

        private String body;

        private String createdAt;

        private String lastModifiedAt;

    }
}
