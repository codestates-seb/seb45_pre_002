package com.codestates.stackoverflow.questionvote.dto;

import lombok.Getter;
import lombok.Setter;

public class QuestionVoteDto {

    @Getter
    @Setter
    public static class PostDto {

        private long member_id;

        private long question_id;

        private boolean like;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private long question_vote_id;

        private long member_id;

        private long question_id;

        private boolean like;
    }
}
