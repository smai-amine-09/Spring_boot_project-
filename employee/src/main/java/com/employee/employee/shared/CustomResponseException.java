package com.employee.employee.shared;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomResponseException extends RuntimeException {
    private HttpStatus code;
    private String message;

    public static CustomResponseException BadCredentials() {
        return new CustomResponseException(HttpStatus.NOT_FOUND, "Bad Credentials");
    }

}
