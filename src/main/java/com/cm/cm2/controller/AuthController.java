package com.cm.cm2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.cm2.entities.User;
import com.cm.cm2.forms.ChangePasswordForm;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
            session.setAttribute("message",new Message(" Your email address has been successfully verified. You can now log in to your account using your credentials.",MessageType.green));
            return "sucess_page";
        }
        session.setAttribute("message", new Message("Email not verified ! Token",MessageType.red));
        return "error_page";
    }

    @GetMapping("/changePassword")
    public String forgetPassword(@RequestParam("token") String token, HttpSession session, ChangePasswordForm changePasswordForm, Model model) {
        User user = userService.getUserByForgetToken(token).orElse(null);
        if (user != null && user.getForgetpasswordToken().equalsIgnoreCase(token)) {
            changePasswordForm.setEmail(user.getEmail());
            model.addAttribute("changePassword", changePasswordForm);
            return "change_password";
        }
        session.setAttribute("message",new Message("Some thing error.",MessageType.red));
        return "error_page";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute ChangePasswordForm changePasswordForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "change_password";
        }
        User user = userService.getUserByEmail(changePasswordForm.getEmail());
        if (user != null) {
            user.setPassword(changePasswordForm.getPassword());
            userService.updateUser(user);
            session.setAttribute("message", new Message(" Your password has been successfully change.",MessageType.green));
            return "sucess_page";

        }
        session.setAttribute("message", new Message("User is not found.",MessageType.red));

        return "error_page";
    }

}
