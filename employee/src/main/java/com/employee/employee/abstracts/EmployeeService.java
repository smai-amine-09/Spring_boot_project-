package com.employee.employee.abstracts;

import java.util.List;

import com.employee.employee.dtos.EmployeeCreate;
import com.employee.employee.dtos.EmployeeUpdate;
import com.employee.employee.entities.Employee;

public interface EmployeeService {
    Employee findOne(Long employeeId);

    List<Employee> findAll();

    void deleteOne(Long employeeId);

    Employee updateOne(Long employeeId, EmployeeUpdate employee);

    Employee createOne(EmployeeCreate employeeCreate);
}
