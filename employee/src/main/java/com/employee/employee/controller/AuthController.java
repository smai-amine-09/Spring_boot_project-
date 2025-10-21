package com.employee.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.dtos.LoginRequest;
import com.employee.employee.dtos.SingupRequest;
import com.employee.employee.services.AuthService;
import com.employee.employee.shared.GlobaleResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/singup")
    public ResponseEntity<?> singup(@RequestBody SingupRequest singupRequest, @RequestParam String token) {
        return new ResponseEntity<>(new GlobaleResponse<>(authService.singup(singupRequest, token), HttpStatus.CREATED),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(new GlobaleResponse<>(authService.login(loginRequest), HttpStatus.CREATED),
                HttpStatus.CREATED);
    }

}
