package com.codestates.stackoverflow.answer.repository;

import com.codestates.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByBody(String body);
}
