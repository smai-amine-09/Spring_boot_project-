package com.employee.employee.shared;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobaleResponse> handelNoResourceFoundException(NoResourceFoundException ex) {
        lombok.var error = List.of(
                new GlobaleResponse.ErrorItem("Resource is not found "));
        return new ResponseEntity<>(new GlobaleResponse<>(error, HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<GlobaleResponse> handelCustomResponseException(CustomResponseException ex) {
        lombok.var error = List.of(
                new GlobaleResponse.ErrorItem(ex.getMessage()));
        return new ResponseEntity<>(new GlobaleResponse<>(error, ex.getCode()),
                ex.getCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobaleResponse> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        lombok.var error = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new GlobaleResponse.ErrorItem(err.getField() + ";" + err.getDefaultMessage()))
                .toList();
        return new ResponseEntity<>(new GlobaleResponse<>(error, HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
}
