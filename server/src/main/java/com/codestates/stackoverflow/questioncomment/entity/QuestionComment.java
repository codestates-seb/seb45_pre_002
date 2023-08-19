package com.codestates.stackoverflow.questioncomment.entity;

import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.entity.Questions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionCommentId;

    private String body;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(targetEntity = Questions.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Questions question;

    public void setMember(Member member) {
        this.member = member;
        if(!member.getQuestionComments().contains(this)) {
            member.getQuestionComments().add(this);
        }
    }

    public void setQuestion(Questions question) {
        this.question = question;
        if(!question.getQuestionComments().contains(this)) {
            question.getQuestionComments().add(this);
        }
    }
}
