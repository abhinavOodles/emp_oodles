package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Exception.DepartmentAlreadyPresent;
import com.employeemanagementsystem.empman.Exception.DepartmentDoesNotExist;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Repository.DepartmentRepo;
import com.employeemanagementsystem.empman.Transformers.DepartmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo  ;
    public String addDepartment(DepartmentEntryDto departmentDto) throws DepartmentAlreadyPresent, DepartmentDoesNotExist {

        Optional<Department> optionalDepartment = departmentRepo.findById(departmentDto.getDepartmentId());

        if (optionalDepartment.isPresent()){
            throw new DepartmentAlreadyPresent("Department is Already Present") ;
        }

        Department department = DepartmentTransformer.convertDtoTOEntity(departmentDto);
        departmentRepo.save(department);

        return "Department Added Successfully." ;
    }
}
