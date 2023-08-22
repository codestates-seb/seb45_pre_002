package com.codestates.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    QUESTION_VOTE_NOT_FOUND(404, "QuestionVote not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    MEMBER_EXISTS(409, "Member exists"),
    ANSWER_EXISTS(409, "Answer exists"),
    QUESTION_EXISTS(409, "Question exists"),
    QUESTION_VOTE_EXISTS(409, "QuestionVote exists"),
    ANSWER_VOTE_EXISTS(409, "AnswerVote exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    PASSWORD_NOT_MATCHED(409, "Password not matched"),
    UNAUTHORIZED_USER(404, "unauthorized user");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
