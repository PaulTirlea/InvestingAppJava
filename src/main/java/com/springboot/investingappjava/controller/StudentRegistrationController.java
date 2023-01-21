package com.springboot.investingappjava.controller;

import com.springboot.investingappjava.model.Role;
import com.springboot.investingappjava.model.Student;
import com.springboot.investingappjava.service.AuthenticationService;
import com.springboot.investingappjava.service.RoleService;
import com.springboot.investingappjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springboot.investingappjava.configuration.SecurityConfig;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class StudentRegistrationController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationService authenticationService;

    @GetMapping("/register/student")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "studentRegistration";
    }
    @PostMapping("/register/student")
    public String registerStudent(@ModelAttribute Student student, @RequestParam String role) {
        Role userRole = roleService.findByName(role);
        student.setRoles(new HashSet<>(Arrays.asList(userRole)));
        student.setPassword(authenticationService.encode(student.getPassword()));
        studentService.save(student);
        return "redirect:/login";
    }
}

