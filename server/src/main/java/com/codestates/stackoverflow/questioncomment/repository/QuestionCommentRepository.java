package com.codestates.stackoverflow.questioncomment.repository;

import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    List<QuestionComment> findByQuestionQuestionIdOrderByQuestionCommentIdDesc(long questionId);
}
