// package com.Auth.Authentication.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // disable CSRF
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/auth/register").permitAll() // allow anyone to register
//                 .requestMatchers("/api/auth/login").permitAll() // allow anyone to login
//                 .anyRequest().authenticated() // everything else needs login
//             );
//         return http.build();
//     }
// }