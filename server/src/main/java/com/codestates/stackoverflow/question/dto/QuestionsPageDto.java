package com.codestates.stackoverflow.question.dto;

import com.codestates.stackoverflow.utils.PageInfo;
import lombok.Getter;
import lombok.Setter;


public class QuestionsPageDto {
    @Getter
    @Setter
    public static class PageResponseDto<T> {
        private T questions;
        private PageInfo pageInfo;
    }
}
