package com.prj666.group1.petadoptionsystem.controller.user;

import com.prj666.group1.petadoptionsystem.dto.user.LoginResponse;
import com.prj666.group1.petadoptionsystem.dto.user.LoginRequest;
import com.prj666.group1.petadoptionsystem.dto.user.RegisterRequest;
import com.prj666.group1.petadoptionsystem.model.user.User;
import com.prj666.group1.petadoptionsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user.isPresent()) {
            // Successful login
            return ResponseEntity.ok(new LoginResponse("Login successful", user.get().getId()));
        } else {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid username or password", null));
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Login Page</title>
                </head>
                <body>
                <h2>Login</h2>
                <form action="/api/users/process-login" method="post">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username"><br><br>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password"><br><br>
                    <button type="submit">Login</button>
                </form>
                </body>
                </html>
                """;
    }

    @PostMapping("/process-login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Optional<User> user = userService.login(username, password);

        if (user.isPresent()) {
            return "Login successful for user: ";
        } else {
            return  "Invalid username or password";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Check if the username already exists
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already taken");
        }

        // Create and save a new user
        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setEmail(registerRequest.getEmail());
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @GetMapping("/test-register")
    public ResponseEntity<String> testRegister(
            @RequestParam String email,
            @RequestParam String password
    ) {

        // Check if the username already exists
        if (userService.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Test user already exists");
        }

        // Create and save the test user
        User testUser = new User();
        testUser.setEmail(email);
        testUser.setPassword(password);
        userService.saveUser(testUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Test user registered successfully");
    }
}