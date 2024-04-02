package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
