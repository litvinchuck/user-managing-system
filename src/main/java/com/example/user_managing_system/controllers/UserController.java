package com.example.user_managing_system.controllers;

import com.example.user_managing_system.dto.UserUpdate;
import com.example.user_managing_system.models.User;
import com.example.user_managing_system.services.AppUserDetailsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final ModelMapper modelMapper;
    private final AppUserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(ModelMapper modelMapper, AppUserDetailsService userDetailsService) {
        this.modelMapper = modelMapper;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/user")
    public String userPage(Model model, Authentication auth) {
        UserUpdate user = modelMapper.map(auth.getPrincipal(), UserUpdate.class);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update-user")
    public String updateUser(
            @Valid @ModelAttribute("user") UserUpdate userDto,
            BindingResult result,
            Model model,
            Authentication auth) {

        String username = auth.getName();
        if (!username.equals(userDto.getEmail().toLowerCase())
                && userDetailsService.userExistsByUsername(userDto.getEmail())) {
            logger.info("user {} already exists", userDto.getEmail());
            result.rejectValue("email", "Bad Request", "User already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/user";
        }

        userDetailsService.updateUser((User) auth.getPrincipal(), userDto);
        return "redirect:/user?success";
    }
}
