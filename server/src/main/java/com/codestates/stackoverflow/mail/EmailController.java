package com.codestates.stackoverflow.mail;

import com.codestates.stackoverflow.member.controller.MemberController;
import com.codestates.stackoverflow.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    @ResponseBody
    public ResponseEntity emailConfirm(@RequestBody EmailDto emailDto) throws Exception {
        String email = emailDto.getEmail();
        emailService.sendSimpleMessage(email);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/auth-code")
    public ResponseEntity postAuthCode(@RequestBody CodeDto codeDto) {
        String code = codeDto.getCode();
        emailService.codeAuthentication(code);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
