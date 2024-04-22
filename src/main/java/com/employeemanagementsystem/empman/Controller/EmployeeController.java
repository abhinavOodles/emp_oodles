package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add-Employee")
    public ResponseEntity<String> addDepartment (@RequestBody empDto empDto){
        try {
            String result =  employeeService.addEmp(empDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-list-of-employee-same-department")
    private List<Employee> employeeList (@RequestParam int departmentId){
        List<Employee> list =  employeeService.getListOfEmployeeInaSameDepartment(departmentId) ;

        return list;
    }



    @PutMapping("/change-the-name-of-employee")
    private ResponseEntity<String> changeName (@RequestParam int employeeId , @RequestParam String name) {
        try{
            String result =   employeeService.changeName(employeeId ,name) ;
            return  new ResponseEntity<>(result , HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST) ;
        }
    }

    @DeleteMapping("/remove-employee")
    private ResponseEntity<String> deleteEmployee (@RequestParam int empId){
        try {
            String result = employeeService.deleteEmployee(empId);
            return new ResponseEntity<>(result , HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST) ;
        }
    }
}