package com.codestates.stackoverflow.questioncomment.repository;

import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
}
