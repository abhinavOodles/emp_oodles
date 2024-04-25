package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {

    @Query(value = "SELECT * FROM departments  WHERE departments.department_name = :departmentName",nativeQuery = true)
    Optional<Department> findByName(@Param("departmentName") String departmentName);
}
