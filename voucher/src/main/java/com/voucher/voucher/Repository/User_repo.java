package com.voucher.voucher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voucher.voucher.Model.User;

public interface User_repo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
