package com.employeemanagementsystem.empman.Controller;


import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Exception.EmployeeDoesNotExist;
import com.employeemanagementsystem.empman.Service.CompanyService;
import com.employeemanagementsystem.empman.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Company")
public class CompanyController {

    @Autowired
    CompanyService companyService ;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/add")
    public ResponseEntity<String> add (@RequestBody addCompanyDto companyDto) {

        companyService.add(companyDto) ;
        return new ResponseEntity<>("Company added successfully" , HttpStatus.CREATED);
    }

    @PutMapping("/change-company-name")
    public ResponseEntity<String> changeCompanyName (@RequestParam int registrationNumber , @RequestParam String name){
        try {
            String result = companyService.changeName(registrationNumber,name);
            return new ResponseEntity<>(result , HttpStatus.ACCEPTED) ;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST) ;
        }
    }

}
