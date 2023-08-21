package com.codestates.stackoverflow.answervote.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerVoteId;

    @Column(name = "is_like")
    private boolean like = false;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(targetEntity = Answer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;
}
