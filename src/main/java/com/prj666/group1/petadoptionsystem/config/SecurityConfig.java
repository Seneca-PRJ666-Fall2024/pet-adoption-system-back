package com.prj666.group1.petadoptionsystem.config;

import com.prj666.group1.petadoptionsystem.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${openapi.petAdoptionSystem.base-path:}")
    private String basePath;

    @Value("${openapi.image-base-path}")
    private String imageBasePath;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF if not using sessions
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOriginPattern("*"); // Allow any origin
                    config.addAllowedMethod("*");       // Allow all HTTP methods
                    config.addAllowedHeader("*");       // Allow all headers
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                basePath +"/adoption/story",
                                basePath+ "/user/login",
                                basePath + "/user/register",
                                imageBasePath + "/**"
                        ).permitAll() // Public endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .formLogin(form -> form.disable()) // Disable form login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Use stateless sessions

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}