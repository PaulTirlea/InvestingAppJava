package com.springboot.investingappjava.controller;

import ch.qos.logback.core.boolex.EvaluationException;
import com.springboot.investingappjava.model.User;
import com.springboot.investingappjava.repository.UserRepository;
import com.springboot.investingappjava.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "login";
        }
    }
}

