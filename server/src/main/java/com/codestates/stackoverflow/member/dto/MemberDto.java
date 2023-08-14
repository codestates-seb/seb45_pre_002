package com.codestates.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class MemberDto {

    @Getter
    @Setter
    public static class PostDto {

        @Email
        private String email;

        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private long member_id;

        private String email;

        private String username;

        private String password;

        private Date created_at;

        private Date last_modified_at;
    }
}
