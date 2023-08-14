package com.codestates.stackoverflow.mail;

import lombok.RequiredArgsConstructor;
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
    public String emailConfirm(@RequestBody EmailDTO email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email.getEmail());
        return confirm;
    }
}
