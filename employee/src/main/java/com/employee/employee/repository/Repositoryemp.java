package com.employee.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.employee.entities.Employee;

@Repository
public interface Repositoryemp extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByAccountCreationToken(String accountCreationToken);
}
