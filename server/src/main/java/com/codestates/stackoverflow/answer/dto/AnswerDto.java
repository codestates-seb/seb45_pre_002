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

//    public static class PatchDto {
//
//    }
}
