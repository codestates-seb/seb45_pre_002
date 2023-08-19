package com.codestates.stackoverflow.question.repository;


import com.codestates.stackoverflow.question.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions,Long> {

}
