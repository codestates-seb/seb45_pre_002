package com.codestates.stackoverflow.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
