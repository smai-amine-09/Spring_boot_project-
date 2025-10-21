package com.employee.employee.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee.abstracts.EmployeeService;
import com.employee.employee.abstracts.LeaveRequestService;
import com.employee.employee.dtos.EmployeeCreate;
import com.employee.employee.dtos.EmployeeUpdate;
import com.employee.employee.dtos.LeaveRequestCreate;
import com.employee.employee.entities.Employee;
import com.employee.employee.entities.LeaveRequest;
import com.employee.employee.shared.GlobaleResponse;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/employees")
public class EmployeeControl {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping()
    public ResponseEntity<?> getAllemployees() {
        return new ResponseEntity<>(new GlobaleResponse<>(employeeService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> findOne(@PathVariable Long employeeId) {
        return new ResponseEntity<>(new GlobaleResponse<>(employeeService.findOne(
                employeeId),
                HttpStatus.OK),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createOne(
            @RequestBody @Valid EmployeeCreate employee) {
        Employee newEmployee = employeeService.createOne(employee);

        return new ResponseEntity<>(new GlobaleResponse<>(newEmployee, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deletOne(@PathVariable Long employeeId) {
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(new GlobaleResponse<>(null, HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateOne(@Valid @RequestBody EmployeeUpdate newEmployee, @PathVariable Long employeeId) {
        return new ResponseEntity<>(
                new GlobaleResponse<>(employeeService.updateOne(employeeId, newEmployee), HttpStatus.OK),
                HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobaleResponse<LeaveRequest>> leaveRequest(
            @RequestBody @Valid LeaveRequestCreate leaveRequest,
            @PathVariable Long employeeId) {

        LeaveRequest newLeaveRequest = leaveRequestService.createOne(leaveRequest, employeeId);

        return new ResponseEntity<>(new GlobaleResponse<>(newLeaveRequest, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leave-requests")
    public ResponseEntity<?> leaveRequestsByEmployeeId(@PathVariable Long employeeId) {
        return new ResponseEntity<>(
                new GlobaleResponse<>(leaveRequestService.getAllByEmployeeId(employeeId), HttpStatus.OK),
                HttpStatus.OK);
    }

}