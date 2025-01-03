package com.cm.cm2.controller;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cm.cm2.entities.User;
import com.cm.cm2.forms.ForgetPasswordForm;
import com.cm.cm2.forms.UserForm;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.EmailService;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private final UserService userService;
    private final EmailService emailService;

    public PageController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);

        return "about";
    }

    @GetMapping(value = "/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping(value = "/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    // @PostMapping(value = "/login")
    // public String loginPagepost() {
    //     return "login";
    // }
    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegisterHandaler(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user1 = userService.getUserByEmail(userForm.getEmail());
        if (user1 != null) {
            session.setAttribute("message", new Message("This Email Id is already present",MessageType.red));

            return "register";
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        user.setEnable(false);
        user.setProfilePic("https://th.bing.com/th/id/R.19fa7497013a87bd77f7adb96beaf768?rik=144XvMigWWj2bw&riu=http%3a%2f%2fwww.pngall.com%2fwp-content%2fuploads%2f5%2fUser-Profile-PNG-High-Quality-Image.png&ehk=%2bat%2brmqQuJrWL609bAlrUPYgzj%2b%2f7L1ErXRTN6ZyxR0%3d&risl=&pid=ImgRaw&r=0");

        User saveUser = userService.saveUser(user);
        Message message = new Message("Registration Successful!Please verify your email before logging in. ",MessageType.green);
        session.setAttribute("message", message);
        System.out.print(saveUser);
        return "redirect:/login";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        ForgetPasswordForm forgetPasswordForm = new ForgetPasswordForm();
        model.addAttribute("forget", forgetPasswordForm);
        return "forget";
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(@Valid @ModelAttribute ForgetPasswordForm forgetPasswordForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("message",new Message("Fill the information",MessageType.red));
            return "forget";
        }
        User user = userService.getUserByEmail(forgetPasswordForm.getEmail());
        if (user == null) {
            session.setAttribute("message",new Message("Email id not found",MessageType.red));
            return "redirect:/login";
        }
        String token = UUID.randomUUID().toString();
        user.setForgetpasswordToken(token);
        userService.updateUser(user);
        emailService.sendEmail(forgetPasswordForm.getEmail(), "For Reset Password", Helper.getLinkForForgetPasswoed(token));
        session.setAttribute("message",new Message("Check your email box",MessageType.green));
        return "redirect:/login";
    }

}
