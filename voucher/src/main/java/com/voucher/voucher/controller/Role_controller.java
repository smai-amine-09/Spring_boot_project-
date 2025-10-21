package com.voucher.voucher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.voucher.Model.Role;
import com.voucher.voucher.Repository.Role_repo;

@RestController
@RequestMapping("/roles")
public class Role_controller {
               @Autowired
    private Role_repo role_repo;

    @PostMapping("/regsiter")
    public Role createRole(@RequestBody Role role) {
        return role_repo.save(role);
    }

    @GetMapping("/AllRoles")
    public List<Role> getAllRoles() {
        return role_repo.findAll();
    }
}
