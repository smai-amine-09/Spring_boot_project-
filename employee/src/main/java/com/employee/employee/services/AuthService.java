package com.employee.employee.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.employee.config.JwtHelper;
import com.employee.employee.dtos.LoginRequest;
import com.employee.employee.dtos.SingupRequest;
import com.employee.employee.entities.Employee;
import com.employee.employee.entities.UserAccount;
import com.employee.employee.repository.Repositoryemp;
import com.employee.employee.repository.UserAccountRepo;
import com.employee.employee.shared.CustomResponseException;

@Service
public class AuthService {
    @Autowired
    private UserAccountRepo userAccountRepo;
    @Autowired
    private Repositoryemp repositoryemp;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;

    public String singup(SingupRequest singupRequest, String token) {
        Employee employee = repositoryemp.findByAccountCreationToken(token)
                .orElseThrow(() -> new CustomResponseException(HttpStatus.BAD_REQUEST, "Invalid token"));

        UserAccount account = new UserAccount();
        account.setUsername(singupRequest.username());
        account.setPassword(passwordEncoder.encode(singupRequest.password()));
        account.setEmployee(employee);
        employee.setAccountCreationToken(null);
        employee.setVerified(true);
        userAccountRepo.save(account);
        return "successful";
    }

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        UserAccount user = userAccountRepo.findOneByUsername(loginRequest.username())
                .orElseThrow(CustomResponseException::BadCredentials);
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getId());
        customClaims.put("role", user.getRole());

        return jwtHelper.generateToken(customClaims, user);
    }

}
