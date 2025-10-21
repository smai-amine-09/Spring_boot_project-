package com.employee.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.employee.employee.abstracts.LeaveRequestService;
import com.employee.employee.dtos.LeaveRequestCreate;
import com.employee.employee.entities.Employee;
import com.employee.employee.entities.LeaveRequest;
import com.employee.employee.repository.LeaveRequestRepo;
import com.employee.employee.repository.Repositoryemp;
import com.employee.employee.shared.CustomResponseException;

@Service
public class LeaveRequestImpl implements LeaveRequestService {
    @Autowired
    LeaveRequestRepo leaveRequestRepo;
    @Autowired
    Repositoryemp repositoryemp;

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, Long employeeId) {
        Optional<Employee> exsistemployee = repositoryemp.findById(employeeId);
        if (exsistemployee.isEmpty()) {
            throw new CustomResponseException(HttpStatus.NOT_FOUND, "Employee with id " + employeeId + " not found");
        }
        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());
        leaveRequest.setEmployee(exsistemployee.get());
        return leaveRequestRepo.save(leaveRequest);
    }

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public List<LeaveRequest> getAllByEmployeeId(Long employeeId) {
        Optional<Employee> exsistemployee = repositoryemp.findById(employeeId);
        if (exsistemployee.isEmpty()) {
            throw new CustomResponseException(HttpStatus.NOT_FOUND, "Employee with id " + employeeId + " not found");
        }
        return leaveRequestRepo.findAllByEmployeeId(employeeId);
    }

}
