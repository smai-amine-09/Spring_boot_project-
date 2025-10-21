package com.employee.employee.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, max = 30, message = "min is 2 characters and max is 30 characters")
    @NotNull(message = "firstname is required ")
    private String firstname;
    @Size(min = 3, max = 30, message = "min is 2 characters and max is 30 characters")
    @NotNull(message = "lastname is required ")
    private String lastname;
    @NotNull(message = "email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String position;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departmentId", nullable = false)
    @JsonProperty("departmentId")
    private Department department;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @Column(name = "account_creation_token")
    private String accountCreationToken;

    public Long getDepartment() {
        return department.getId();
    }
}
