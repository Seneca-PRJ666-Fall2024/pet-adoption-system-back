package com.prj666.group1.petadoptionsystem.service.user;

import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        // Perform password verification logic here
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String newToken = generateToken(user.get().getEmail());
            user.get().setToken(newToken);
            userRepository.save(user.get());
            return user;
        }
        return Optional.empty();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String generateToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 60*60*1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }
}