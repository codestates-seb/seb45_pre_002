package com.codestates.stackoverflow.answervote.repository;

import com.codestates.stackoverflow.answervote.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
