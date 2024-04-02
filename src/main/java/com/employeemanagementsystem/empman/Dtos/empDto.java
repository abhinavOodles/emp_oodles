package com.employeemanagementsystem.empman.Dtos;

import com.employeemanagementsystem.empman.Enums.Gender;
import com.employeemanagementsystem.empman.Models.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class empDto {

    private String empName;
    private int age;
    private Gender gender;
    private int deptId;
    private Department department;
}
