package com.codestates.stackoverflow.question.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.member.entity.Member;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import com.codestates.stackoverflow.questionvote.entity.QuestionVote;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Questions extends TimeStamp{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    //@ToString.Exclude
    @Setter
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
    private Long viewCount;

    private Long voteCount;

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }


    @ElementCollection
    public List<Long> upVotedUserId = new ArrayList<>();

    @ElementCollection
    public List<Long> downVotedUserId = new ArrayList<>();

    @Builder
    public Questions(Long questionId, Member member, String questionBody, String questionTitle) {

        this.questionId = questionId;
        this.member = member;
        this.questionBody = questionBody;
        this.questionTitle = questionTitle;
        this.viewCount = 0L;
        this.voteCount = 0L;

    }

    @ElementCollection
    public List<Long> upVotedMemberId = new ArrayList<>();

    @ElementCollection
    public List<Long> downVotedMemberId = new ArrayList<>();

    public void updateViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public void updateVoteCount (Long voteCount) {
        this.voteCount = voteCount;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setQuestionBody (String questionBody) {
        this.questionBody = questionBody;
    }

}