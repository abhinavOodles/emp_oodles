package com.employeemanagementsystem.empman.Repository;

import com.employeemanagementsystem.empman.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

//    @Query(value = "SELECT * from employee" , nativeQuery = true)
//    public List<Employee> findAll ();

    @Query(value = "SELECT * FROM employee order by employee_name " , nativeQuery = true)
    public List<Employee> findAll() ;

//    @Query(value = "SELECT * FROM employee where employee_name = 'abcd' order by employee_name " , nativeQuery = true)
//    public List<Employee> findAll() ;


}
