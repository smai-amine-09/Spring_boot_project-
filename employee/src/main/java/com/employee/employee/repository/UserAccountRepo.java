package com.employee.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.employee.entities.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findOneByUsername(String username);

    @Query("""
            SELECT COUNT(u) > 0 FROM UserAccount u
            WHERE u.username = :username AND u.employee.id = :employeeId
            """)

    public boolean isOwner(@Param("username") String username, @Param("employeeId") Long employeeId);
}
