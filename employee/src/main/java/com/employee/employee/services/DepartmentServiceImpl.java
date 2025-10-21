package com.employee.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.employee.employee.abstracts.DepartmentService;
import com.employee.employee.entities.Department;
import com.employee.employee.repository.DepartmentRepo;
import com.employee.employee.shared.CustomResponseException;;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public Department findOne(Long departmentId) {
        Optional<Department> existingDepartment = departmentRepo.findById(departmentId);
        if (existingDepartment.isEmpty()) {
            throw new CustomResponseException(HttpStatus.NOT_FOUND,
                    "Department with id " + departmentId + " not found");
        }
        return existingDepartment.get();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department createOne(Department department) {
        Optional<Department> existingDepartment = departmentRepo.findByName(department.getName());
        if (existingDepartment.isPresent()) {
            throw new CustomResponseException(HttpStatus.CONFLICT,
                    "Department with  email " + department.getName() + " already exists");
        }
        return departmentRepo.save(department);
    }

    @Override
    public void deleteOne(Long departmentId) {
        Optional<Department> existingDepartment = departmentRepo.findById(departmentId);
        if (existingDepartment.isPresent()) {
            departmentRepo.deleteById(departmentId);
            return;
        }
        throw new CustomResponseException(HttpStatus.NOT_FOUND, "Department with id " + departmentId + " not found");
    }

    @Override
    public Department updateOne(Long departmentId, Department newdepartment) {
        Optional<Department> existingDepartment = departmentRepo.findById(departmentId);
        if (existingDepartment.isPresent()) {
            Optional<Department> existingName = departmentRepo.findByName(newdepartment.getName());
            if (existingName.isPresent()) {
                throw new CustomResponseException(HttpStatus.CONFLICT,
                        "Department with  name " + newdepartment.getName() + " already exists ,pls change name");
            }
            Department department = new Department();
            department.setName(newdepartment.getName());
            return departmentRepo.save(department);
        }
        throw new CustomResponseException(
                HttpStatus.NOT_FOUND, "Department with id " + departmentId + " not found");
    }
}
