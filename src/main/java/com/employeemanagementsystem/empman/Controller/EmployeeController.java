package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Enums.Designation;
import com.employeemanagementsystem.empman.Enums.Gender;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add-Employee")
    public ResponseEntity<String> addEmployee(@RequestBody empDto empDto){
        try {
            String result =  employeeService.addEmp(empDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list-of-emp-in-a-company")
    private ResponseEntity<?> getListOfEmployeeInACompany(@RequestParam int regsID){
        try {
            List<Employee> employeeList = employeeService.getListOfEmployeeInACompany(regsID);
            return new ResponseEntity<>(employeeList,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>("CompanyId is not correct" , HttpStatus.NOT_FOUND) ;
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

    @DeleteMapping("/remove-all-employee")
    public ResponseEntity<String> deleteAllEmployee(){
        try {
            String res = employeeService.deleteAllEmployee() ;
            return new ResponseEntity<>(res , HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Some Error Occurs" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-list-Acc-Gender")
    private List<Employee> getEmployeeAccToGender(@RequestParam Gender gender){
        return employeeService.getListOfEmpAccToGender(gender);
    }

    @GetMapping("/get-list-of-emp")
    public List<Employee> getListOfEmployee (){
            return employeeService.getAllEmployee() ;

    }

    @PutMapping("/change-the-company")
    public ResponseEntity<String> changeTheCompany(@RequestParam int id , @RequestParam String name , @RequestParam Designation designation){
        try {
            String res = employeeService.changeTheCompany(id,name,designation) ;
            return new ResponseEntity<>(res , HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-the-other-details-of-employee")
    public ResponseEntity<String> fillTheLeftOutDetails(@RequestParam int id , @RequestParam String companyName , @RequestParam Designation designation , @RequestParam int departmentId , @RequestParam String departmentName , @RequestParam Gender gender){
        try {
            String res = employeeService.fillTheLeftOutDetails(id,companyName,designation,departmentId,departmentName,gender) ;
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }


}