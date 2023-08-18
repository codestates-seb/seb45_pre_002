package com.codestates.stackoverflow.answer.mapper;

import com.codestates.stackoverflow.answer.dto.AnswerDto;
import com.codestates.stackoverflow.answer.entity.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public Answer postToAnswer(AnswerDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Answer answer = new Answer();
            answer.setBody(postDto.getBody());

            return answer;
        }
    }
}
