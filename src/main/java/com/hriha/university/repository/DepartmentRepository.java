package com.hriha.university.repository;

import com.hriha.university.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select d from Department d where d.name = :n")
    Department findByName(String name);
}
