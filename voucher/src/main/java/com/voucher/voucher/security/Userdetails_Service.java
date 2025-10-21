// package com.voucher.voucher.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.stereotype.Service;

// import com.voucher.voucher.Model.User;
// import com.voucher.voucher.Repository.User_repo;

// @Service
// public class Userdetails_Service implements UserDetailsService {

// User_repo user_repo;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// com.voucher.voucher.Model.User user = user_repo.findByEmail(username);
// if (user == null) {
// throw new UnsupportedOperationException("Unimplemented method
// 'loadUserByUsername'");
// }
// return new
// org.springframework.security.core.userdetails.User(user.getEmail(),
// user.getPassword(),
// user.getRoles());
// }

// }
