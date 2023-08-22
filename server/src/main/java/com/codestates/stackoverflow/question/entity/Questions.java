package com.codestates.stackoverflow.question.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Questions extends Auditable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long questionId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(targetEntity = QuestionComment.class, mappedBy = "question")
    private List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany(targetEntity = Answer.class, mappedBy = "question")
    private List<Answer> answer = new ArrayList<>();

    @OneToMany(targetEntity = QuestionVote.class, mappedBy = "question")
    private List<QuestionVote> questionVotes = new ArrayList<>();

    private String questionTitle;

    private String questionBody;

    // 조회수
    @Column (columnDefinition = "integer default 0", nullable = false)
    private long viewCount;

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void updateViewCount(long viewCount) {
        this.viewCount = viewCount;
    }
}