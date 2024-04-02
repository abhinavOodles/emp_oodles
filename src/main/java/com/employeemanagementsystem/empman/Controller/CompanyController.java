package com.employeemanagementsystem.empman.Controller;


import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Company")
public class CompanyController {

    @Autowired
    CompanyService companyService ;

    @PostMapping("/add")
    public ResponseEntity<String> add (@RequestBody addCompanyDto companyDto) {
        companyService.add(companyDto) ;
        return new ResponseEntity<>("Company added successfully" , HttpStatus.CREATED);
    }

}
