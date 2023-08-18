package com.codestates.stackoverflow.questioncomment.mapper;

import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.questioncomment.dto.QuestionCommentDto;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import org.springframework.stereotype.Component;

@Component
public class QuestionCommentMapper {

    public QuestionComment postToQuestionComment(QuestionCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            QuestionComment questionComment = new QuestionComment();
            questionComment.setBody(postDto.getBody());
            questionComment.setQuestion(postToQuestion(postDto));

            return questionComment;
        }
    }

    private Questions postToQuestion(QuestionCommentDto.PostDto postDto) {
        if(postDto == null) {
            return null;
        }
        else {
            Questions question = new Questions();
            //question.setQuestionId(postDto.getQuestionId());

            return question;
        }
    }
}
