package com.employee.employee.abstracts;

import java.util.List;

import com.employee.employee.entities.Department;

public interface DepartmentService {
    Department findOne(Long departmentId);

    List<Department> findAll();

    Department createOne(Department department);

    void deleteOne(Long departmentId);

    Department updateOne(Long departmentId, Department newdepartment);

}
