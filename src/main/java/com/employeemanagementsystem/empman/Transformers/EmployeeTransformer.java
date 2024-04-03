package com.employeemanagementsystem.empman.Transformers;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Models.Employee;

public class EmployeeTransformer {
    public static Employee covertDtoToEntity (empDto emp){
        Employee employee = Employee.builder()
                .employeeId(emp.getEmpId())
                .employeeName(emp.getEmpName())
                .age(emp.getAge())
                .gender(emp.getGender())
                .departmentId(emp.getDeptId())
                .designation(emp.getDesignation())
                .departmentName(emp.getDepartmentName())
                .companyName(emp.getCompanyName())
                .build() ;

        return employee;
    }
}
