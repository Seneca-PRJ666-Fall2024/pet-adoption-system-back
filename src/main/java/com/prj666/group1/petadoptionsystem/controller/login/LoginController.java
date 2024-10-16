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

    @PostMapping("/perform-login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Optional<User> user = userService.login(username, password);

        if (user.isPresent()) {
            model.addAttribute("message", "Login successful for user: " + username);
        } else {
            model.addAttribute("message", "Invalid username or password");
        }
        return "login"; // Renders the login page again with a message
    }

}
