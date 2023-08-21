package com.codestates.stackoverflow.answervote.dto;

import lombok.Getter;
import lombok.Setter;

public class AnswerVoteDto {

    @Getter
    @Setter
    public static class PostDto {

        private long member_id;

        private long answer_id;

        private boolean like;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private long answer_vote_id;

        private long member_id;

        private long answer_id;

        private boolean like;
    }
}
