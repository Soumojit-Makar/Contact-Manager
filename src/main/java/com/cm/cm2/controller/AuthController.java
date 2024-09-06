package com.cm.cm2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.cm2.entities.User;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/varify-email")
    public String varifyEmail(@RequestParam("token") String token, HttpSession session) {
        User user = userService.getUserByToken(token).orElse(null);
        if (user != null && user.getEmailToken().equals(token)) {
            user.setEmailVefied(true);
            user.setEnable(true);
            userService.updateUser(user);
            session.setAttribute("message", Message.builder().contant(" Your email address has been successfully verified. You can now log in to your account using your credentials.").type(MessageType.green).build());
            return "sucess_page";
        }
        session.setAttribute("message", Message.builder().contant("Email not verified ! Token").type(MessageType.red).build());
        return "error_page";
    }

}
