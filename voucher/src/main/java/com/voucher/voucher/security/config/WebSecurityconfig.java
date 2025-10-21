// package com.voucher.voucher.security.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class WebSecurityconfig {
// @Bean
// PasswordEncoder bCryptPasswordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http.httpBasic();
// // http.authorizeHttpRequests()
// // .requestMatchers(HttpMethod.GET, "/voucherapi/vouchers/**")
// // .hasAnyRole("USER","ADMIN")
// // .requestMatchers(HttpMethod.POST, "/voucherapi/vouchers")
// // .hasRole("ADMIN")
// // .requestMatchers(HttpMethod.POST,
// "/user/register").permitAll().and().csrf().disable();
// return http.build();
// }
// }
