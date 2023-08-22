package com.codestates.stackoverflow.question.repository;


import com.codestates.stackoverflow.question.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {

    Optional<List<Questions>> findByQuestionTitleContaining(String title);
    Optional<List<Questions>> findAllByOrderByCreatedAtDesc();

}
