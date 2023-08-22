package com.codestates.stackoverflow.answercomment.repository;

import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
    List<AnswerComment> findByAnswerAnswerIdOrderByAnswerCommentIdDesc(long answerId);

    Optional<AnswerComment> findByAnswerAnswerIdAndMemberMemberId(long answerId, long memberId);
}
