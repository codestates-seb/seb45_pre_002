package com.codestates.stackoverflow.answer.dto;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class AnswerDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDto {

        private long member_id;

        private long question_id;

        @NotBlank(message = "내용을 입력해 주세요.")
        private String body;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatchDto {

        private long member_id;

        @NotBlank(message = "내용을 입력해 주세요.")
        private String body;

    }

    @Getter
    @Setter
    public static class DeleteDto {

        @Positive
        private long member_id;

        @Positive
        private long question_id;

        @Positive
        private long answer_id;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {

        private long answer_id;

        private long member_id;

        private long question_id;

        private boolean accepted;

        private String body;

        private String created_at;

        private String last_modified_at;

    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListResponseDto {

        private List<ResponseDto> answers;
    }
}
