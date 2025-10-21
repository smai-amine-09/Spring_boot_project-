package com.Auth.Authentication.service;

import com.Auth.Authentication.model.User;
import com.Auth.Authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

   

    public boolean authenticate(String name, String password) {
        Optional<User> optionalUser = userRepository.findByName(name);
        return optionalUser.map(user -> user.getPassword().equals(password)).orElse(false);
    }
}
