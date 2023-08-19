package com.codestates.stackoverflow.mail;

public interface EmailService {
    void sendSimpleMessage(String to) throws Exception;
    void codeAuthentication(String ePw);
}
