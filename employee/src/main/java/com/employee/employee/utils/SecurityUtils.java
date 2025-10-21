package com.employee.employee.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.employee.employee.repository.UserAccountRepo;

@Component
public class SecurityUtils {
    @Autowired
    private UserAccountRepo userAccountRepo;

    public boolean isOwner(Long employeeId) {
        final org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userAccountRepo.isOwner(userDetails.getUsername(), employeeId);
    }
}
