package com.example.user_managing_system.controllers;

import com.example.user_managing_system.dto.UserSignUp;
import com.example.user_managing_system.services.AppUserDetailsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AppUserDetailsService userDetailsService;

    @Autowired
    public LoginController(AppUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/login-form")
    public String loginForm(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            logger.info("user {} already authenticated", auth.getPrincipal());
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String registrationForm(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            logger.info("user {} already authenticated", auth.getPrincipal());
            return "redirect:/";
        }
        UserSignUp user = new UserSignUp();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String registration(
            @Valid @ModelAttribute("user") UserSignUp userDto,
            BindingResult result,
            Model model) {

        if (userDetailsService.userExistsByUsername(userDto.getEmail())) {
            logger.info("user {} already exists", userDto.getEmail());
            result.rejectValue("email", "Bad Request", "User already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/signup";
        }

        userDetailsService.signUp(userDto);
        return "redirect:/signup?success";
    }
}
