package com.springboot.investingappjava.controller;

import com.springboot.investingappjava.model.Investor;
import com.springboot.investingappjava.model.Role;
import com.springboot.investingappjava.service.AuthenticationService;
import com.springboot.investingappjava.service.InvestorService;
import com.springboot.investingappjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class InvestorRegistrationController {
    @Autowired
    private InvestorService investorService;
    @Autowired
    private RoleService roleService;
    private AuthenticationService authenticationService;

    @GetMapping("/register/investor")
    public String registerInvestor(Model model) {
        model.addAttribute("investor", new Investor());
        return "investorRegistration";
    }
    @PostMapping("/register/investor")
    public String registerInvestor(@ModelAttribute Investor investor, @RequestParam String role) {
        Role userRole = roleService.findByName(role);
        investor.setRoles(new HashSet<>(Arrays.asList(userRole)));
        investor.setPassword(authenticationService.encode(investor.getPassword()));
        investorService.save(investor);
        return "redirect:/login";
    }
}
