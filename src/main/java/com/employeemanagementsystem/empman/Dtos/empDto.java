package com.employeemanagementsystem.empman.Dtos;

import com.employeemanagementsystem.empman.Enums.Designation;
import com.employeemanagementsystem.empman.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class empDto {
    private int empId ;
    private String empName;
    private int age;
    private Gender gender;
    private int deptId;
    private Designation designation;
    private String departmentName ;
    private String companyName ;

}
