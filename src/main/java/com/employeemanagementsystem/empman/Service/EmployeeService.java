package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Enums.Gender;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.DepartmentRepo;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    public String addEmp(empDto empDto) {
    int deptId=empDto.getDeptId();
    Optional<Department> departmentOptional=departmentRepo.findById(deptId);
    Department department=null;
    if (!departmentOptional.isEmpty()){
        department=departmentOptional.get();
        Integer noOfEmployees=department.getNumberOfEmployeeInDepartments();
        Integer currCount=noOfEmployees++;
        department.setNumberOfEmployeeInDepartments(currCount);
    }
    String name=empDto.getEmpName();
    int age=empDto.getAge();
    Gender gender=empDto.getGender();

    //foreign key

    }
}
