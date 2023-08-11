package com.codestates.stackoverflow.member.entity;

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long member_id;

    private String email;

    private String username;

    private String password;

    private Date created_at = Calendar.getInstance().getTime();

    private Date last_modified_at = Calendar.getInstance().getTime();
}
