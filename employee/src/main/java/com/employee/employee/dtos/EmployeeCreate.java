package com.employee.employee.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeCreate(
        @NotNull(message = "first name is required") @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 characters") String firstName,

        @NotNull(message = "last name is required") @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 characters") String lastName,

        @NotNull(message = "last name is required") @Email(message = "Invalid email format") String email,

        @NotNull(message = "last name is required") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format") String phoneNumber,

        @NotNull(message = "last name is required") @PastOrPresent(message = "Hire date cannot be in the future") LocalDate hireDate,

        @NotNull(message = "last name is required") @Size(min = 2, max = 50, message = "min is 2 characters and max is 50 characters") String position,

        @NotNull(message = "last name is required") Long departmentId) {

}
