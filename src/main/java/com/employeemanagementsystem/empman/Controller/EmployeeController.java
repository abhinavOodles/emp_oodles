package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<String> addDepartment (@RequestBody empDto empDto){
        try {
            String result =  employeeService.addEmp(empDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
