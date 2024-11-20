package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.auth.JwtTokenProvider;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.UserRepository;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        // Perform password verification logic here
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String newToken = jwtTokenProvider.generateToken(user.get().getEmail());
            user.get().setToken(newToken);
            userRepository.save(user.get());
            return user;
        }
        return Optional.empty();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserFromContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            boolean isAnonymous = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ANONYMOUS"));


            if (isAnonymous) {
                throw new IllegalArgumentException("Anonymous user");
            }

            if(authentication.getPrincipal() instanceof User){
                return (User) authentication.getPrincipal();
            } else {
                throw new IllegalArgumentException("Unauthenticated user");
            }
        } else {
            throw new IllegalArgumentException("Unauthenticated user");
        }
    }
}