package com.employee.employee.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.employee.employee.abstracts.EmployeeService;
import com.employee.employee.dtos.EmployeeCreate;
import com.employee.employee.dtos.EmployeeUpdate;
import com.employee.employee.entities.Department;
import com.employee.employee.entities.Employee;
import com.employee.employee.repository.DepartmentRepo;
import com.employee.employee.repository.Repositoryemp;
import com.employee.employee.shared.CustomResponseException;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    Repositoryemp repositoryemp;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    EmailService emailService;

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public Employee findOne(Long employeeId) {
        Optional<Employee> existingEmployee = repositoryemp.findById(employeeId);
        if (existingEmployee.isEmpty()) {
            throw new CustomResponseException(HttpStatus.NOT_FOUND, "Employee with id " + employeeId + " not found");
        }
        return existingEmployee.get();
    }

    @Override
    public List<Employee> findAll() {
        return repositoryemp.findAll();
    }

    @Override
    public void deleteOne(Long employeeId) {
        Optional<Employee> existingEmployee = repositoryemp.findById(employeeId);
        if (existingEmployee.isPresent()) {
            repositoryemp.deleteById(employeeId);
            return;
        }
        throw new CustomResponseException(HttpStatus.NOT_FOUND, "Employee with id " + employeeId + " not found");
    }

    @Transactional
    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();

        Optional<Employee> existingEmployee = repositoryemp.findByEmail(employeeCreate.email());
        if (existingEmployee.isPresent()) {
            throw new CustomResponseException(HttpStatus.CONFLICT,
                    "Employee with  email " + employee.getEmail() + " already exists");
        }
        Department department = departmentRepo.findById(employeeCreate.departmentId())
                .orElseThrow(() -> new CustomResponseException(HttpStatus.NOT_FOUND,
                        "Department with id " + employeeCreate.departmentId() + " not found"));
        try {
            String token = UUID.randomUUID().toString();
            employee.setFirstname(employeeCreate.firstName());
            employee.setLastname(employeeCreate.lastName());
            employee.setPosition(employeeCreate.position());
            employee.setPhoneNumber(employeeCreate.phoneNumber());
            employee.setEmail(employeeCreate.email());
            employee.setAccountCreationToken(token);
            employee.setDepartment(department);
            emailService.sendAccountCreationEmail(employeeCreate.email(), token);
            return repositoryemp.save(
                    employee);
        } catch (Exception e) {
            throw new CustomResponseException(HttpStatus.BAD_REQUEST, "Employee creation failed");
        }

    }

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    @Override
    public Employee updateOne(Long employeeId, EmployeeUpdate newEmployee) {
        Optional<Employee> existingEmployee = repositoryemp.findById(employeeId);
        if (existingEmployee.isPresent()) {

            Employee employee = existingEmployee.get();
            employee.setFirstname(newEmployee.firstName());
            employee.setLastname(newEmployee.lastName());
            employee.setPhoneNumber(newEmployee.phoneNumber());
            employee.setPosition(newEmployee.position());
            return repositoryemp.save(
                    employee);
        }
        throw new CustomResponseException(
                HttpStatus.NOT_FOUND, "Employee with id " + employeeId + " not found");
    }
}
