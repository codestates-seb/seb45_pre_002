package com.codestates.stackoverflow.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginDto {

    @Getter
    @Setter
    public static class PostDto {
        private String email;

        private String password;
    }

    @Getter
    @Setter
    public static class ResponseDto {

        private long member_id;

        private String email;

        private String username;
    }
}
