package com.codestates.stackoverflow.member.entity;

import com.codestates.stackoverflow.audit.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long member_id;

    private String email;

    private String username;

    private String password;

    private boolean userStatus = false;

    private String code;

//    private Date createdAt = Calendar.getInstance().getTime();

//    private Date lastModifiedAt = Calendar.getInstance().getTime();
}
