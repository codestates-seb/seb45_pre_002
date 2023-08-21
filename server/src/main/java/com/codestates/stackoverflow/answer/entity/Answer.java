package com.codestates.stackoverflow.answer.entity;


import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.answervote.entity.AnswerVote;
import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.question.entity.Questions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @OneToMany(targetEntity = AnswerComment.class, mappedBy = "answer")
    private List<AnswerComment> answerComments = new ArrayList<>();

    @OneToMany(targetEntity = AnswerVote.class, mappedBy = "answer")
    private List<AnswerVote> answerVotes = new ArrayList<>();

    private boolean Accepted;

    public void setMember(Member member) {
        this.member = member;
        if(!member.getAnswers().contains(this)) {
            member.getAnswers().add(this);
        }
    }

    public void setQuestion(Questions question) {
        this.question = question;
        if(!question.getAnswer().contains(this)) {
            question.getAnswer().add(this);
        }
    }
}
