package com.codestates.stackoverflow.answercomment.repository;

import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
    List<AnswerComment> findByAnswerAnswerIdOrderByAnswerCommentIdDesc(long answerId);
}
