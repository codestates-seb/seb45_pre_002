package com.codestates.stackoverflow.member.entity;

import com.codestates.stackoverflow.answer.entity.Answer;
import com.codestates.stackoverflow.answercomment.entity.AnswerComment;
import com.codestates.stackoverflow.audit.Auditable;
import com.codestates.stackoverflow.question.entity.Questions;
import com.codestates.stackoverflow.questioncomment.entity.QuestionComment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    private String email;

    private String username;

    private String password;

    private String code;

    private boolean userStatus = false;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(targetEntity = Questions.class, mappedBy = "member")
    private List<Questions> questions = new ArrayList<>();

    @OneToMany(targetEntity = Answer.class, mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(targetEntity = QuestionComment.class, mappedBy = "member")
    private List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany(targetEntity = AnswerComment.class, mappedBy = "member")
    private List<AnswerComment> answerComments = new ArrayList<>();
}
