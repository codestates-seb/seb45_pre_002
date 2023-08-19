package com.codestates.stackoverflow.answercomment.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AnswerComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerCommentId;

    private String body;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(targetEntity = Answer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public void setMember(Member member) {
        this.member = member;
        if(!member.getAnswerComments().contains(this)) {
            member.getAnswerComments().add(this);
        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        if(!member.getAnswerComments().contains(this)) {
            member.getAnswerComments().add(this);
        }
    }
}
