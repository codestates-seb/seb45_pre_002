package com.codestates.stackoverflow.answer.entity;


import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;


    private String body;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member memberId;

//    @ManyToOne
//    @JoinColumn(name = "questionId")
//    private Question questionId;
}
