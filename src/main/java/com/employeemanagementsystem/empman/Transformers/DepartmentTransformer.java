package com.employeemanagementsystem.empman.Transformers;

import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Models.Department;

public class DepartmentTransformer {

    public static Department convertDtoTOEntity(DepartmentEntryDto departmentEntryDto){
        Department department = Department.builder()
                .departmentId((departmentEntryDto.getDepartmentId()))
                .departmentName(departmentEntryDto.getDepartmentName())

                .build();

        return department ;
    }
}
