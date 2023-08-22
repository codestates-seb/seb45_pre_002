package com.codestates.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDto {

        @Column(name = "email", nullable = false, unique = true)
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        @Pattern(regexp = "^(?!\\s*$)[a-z0-9_-]+@[a-z0-9.]+\\.[a-z]{2,4}$", message = "공백이 들어갈 수 없으며 소문자, 숫자, 일부 특수문자(-,_)만 사용가능합니다.")
        private String email;

        @Column(name = "username", nullable = false, unique = true)
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        @Pattern(regexp = "^(?!\\s*$)[a-zA-Z0-9가-힣-_]$", message = "분리된 자모음 혹은 사용할 수 없는 특수문자가 들어있습니다.")
        @Size(min = 2, max = 8, message = "닉네임은 2자리 이상 8자리 이하여야 합니다.")
        private String username;

        @Column(name = "password", nullable = false)
        @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
        @Pattern(regexp = "^(?!\\s*$)((?=.*[a-zA-Z])|(?=.*\\d)|(?=.*\\W))$", message = "하나 이상의 영문, 특수문자, 숫자가 조합되어야 합니다.")
        @Size(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하여야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {

        private long member_id;

        private String email;

        private String username;

        private String created_at;

        private String last_modified_at;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatchDto {

        private long member_id;

        @Column(name = "email", nullable = false, unique = true)
        @Pattern(regexp = "^(?!\\s*$)[a-z0-9_-]+@[a-z0-9.]+\\.[a-z]{2,4}$", message = "공백이 들어갈 수 없으며 소문자, 숫자, 일부 특수문자(-,_)만 사용가능합니다.")
        private String email;

        @Column(name = "username", nullable = false, unique = true)
        @Pattern(regexp = "^(?!\\s*$)[a-zA-Z0-9가-힣-_].{8,16}$", message = "분리된 자모음 혹은 사용할 수 없는 특수문자가 들어있습니다.")
        //@Size(min = 2, max = 8, message = "닉네임은 2자리 이상 8자리 이하여야 합니다.")
        private String username;

        @Column(name = "password", nullable = false)
        @Pattern(regexp = "^(?!\\s*$)((?=.*[a-zA-Z])|(?=.*\\d)|(?=.*\\W))$", message = "하나 이상의 영문, 특수문자, 숫자가 조합되어야 합니다.")
        @Size(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하여야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    public static class ChangePasswordDto {

        @NotBlank
        //@Pattern(regexp = "^(?!\\s*$)((?=.*[a-zA-Z])|(?=.*\\d)|(?=.*\\W))$", message = "하나 이상의 영문, 특수문자, 숫자가 조합되어야 합니다.")
        @Size(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).+$", message = "하나 이상의 영문, 숫자, 특수문자가 조합되어야 합니다.")
        private String old_password;

        @NotBlank
        //@Pattern(regexp = "^(?!\\s*$)((?=.*[a-zA-Z])|(?=.*\\d)|(?=.*\\W))$", message = "하나 이상의 영문, 특수문자, 숫자가 조합되어야 합니다.")
        @Size(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).+$", message = "하나 이상의 영문, 숫자, 특수문자가 조합되어야 합니다.")
        private String new_password;
    }
}
