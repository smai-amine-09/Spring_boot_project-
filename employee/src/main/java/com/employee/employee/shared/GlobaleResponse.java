package com.employee.employee.shared;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GlobaleResponse<T> {
    public enum Case {
        ERROR("error"), SUCCESS("success");

        private final String descriptionCase;

        Case(String descriptionCase) {
            this.descriptionCase = descriptionCase;
        }

        public String getDescription() {
            return descriptionCase;
        }
    }

    private HttpStatus status;
    private T data;
    private List<ErrorItem> errors;

    public record ErrorItem(String message) {
    }

    public GlobaleResponse(List<ErrorItem> errors, HttpStatus status) {
        this.data = null;
        this.status = status;
        this.errors = errors;
    }

    public GlobaleResponse(T data, HttpStatus status) {
        this.data = data;
        this.errors = null;
        this.status = status;
    }

}
