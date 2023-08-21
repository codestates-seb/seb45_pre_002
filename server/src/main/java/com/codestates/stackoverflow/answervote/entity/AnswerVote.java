package com.codestates.stackoverflow.answervote.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerVoteId;

    private boolean like = false;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(targetEntity = Answer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
