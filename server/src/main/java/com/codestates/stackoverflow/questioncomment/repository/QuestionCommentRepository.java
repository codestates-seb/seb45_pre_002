package com.codestates.stackoverflow.questioncomment.repository;

import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
    List<QuestionComment> findByQuestionQuestionIdOrderByQuestionCommentIdDesc(long questionId);

    Optional<QuestionComment> findByQuestionQuestionIdAndMemberMemberId(long questionId, long memberId);
}
