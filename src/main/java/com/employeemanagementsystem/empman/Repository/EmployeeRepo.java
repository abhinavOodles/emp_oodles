package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
