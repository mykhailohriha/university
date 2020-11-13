package com.hriha.university.service;

import com.hriha.university.entity.Department;
import com.hriha.university.exception.DepartmentNotFoundException;
import com.hriha.university.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findByName(String name) throws DepartmentNotFoundException {
        Department department = departmentRepository.findByName(name);

        if (department == null) {
            throw new DepartmentNotFoundException("Department not found.");
        }
        return department;
    }

    public void save(Department department) {
        if (department != null) {
            departmentRepository.save(department);
        }
    }

}
