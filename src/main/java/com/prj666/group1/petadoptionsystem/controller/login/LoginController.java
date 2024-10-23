package com.prj666.group1.petadoptionsystem.controller.login;

import com.prj666.group1.petadoptionsystem.model.user.User;
import com.prj666.group1.petadoptionsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This returns the login.html template
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // This returns the login.html template
    }

}
