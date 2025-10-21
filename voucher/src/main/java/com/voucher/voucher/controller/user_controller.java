package com.voucher.voucher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.voucher.Model.User;
import com.voucher.voucher.Repository.User_repo;

@RestController
@RequestMapping("/user")
public class user_controller {
    @Autowired
    User_repo user_repo;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User existingUser = user_repo.findByEmail(user.getEmail());
        if (existingUser != null) {
            return new ResponseEntity<>("user is exist .", HttpStatus.BAD_REQUEST);
        }
        user_repo.save(user);
        return new ResponseEntity<>("Registration successfull .", HttpStatus.OK);
    }
}
