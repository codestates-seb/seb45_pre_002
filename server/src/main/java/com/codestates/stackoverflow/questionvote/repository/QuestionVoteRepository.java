package com.codestates.stackoverflow.questionvote.repository;

import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
}
