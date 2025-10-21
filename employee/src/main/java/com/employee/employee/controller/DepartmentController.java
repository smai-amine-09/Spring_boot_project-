package com.employee.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.abstracts.DepartmentService;
import com.employee.employee.entities.Department;
import com.employee.employee.shared.GlobaleResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new GlobaleResponse<>(departmentService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<?> findOne(@PathVariable Long departmentId) {
        return new ResponseEntity<>(new GlobaleResponse<>(departmentService.findOne(departmentId), HttpStatus.OK),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobaleResponse<Department>> createOne(
            @RequestBody @Valid Department department) {
        Department newDepartment = departmentService.createOne(department);

        return new ResponseEntity<>(new GlobaleResponse<>(newDepartment, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long departmentId) {
        departmentService.deleteOne(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<?> updateOne(@Valid @RequestBody Department newDepartment, @PathVariable Long departmentId) {
        return new ResponseEntity<>(
                new GlobaleResponse<>(departmentService.updateOne(
                        departmentId, newDepartment), HttpStatus.OK),
                HttpStatus.OK);
    }
}
