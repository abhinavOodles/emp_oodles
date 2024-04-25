package com.employeemanagementsystem.empman.Controller;


import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Models.Company;
import com.employeemanagementsystem.empman.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService ;

    @PostMapping("/add-company")
    public ResponseEntity<String> addCompany (@RequestBody addCompanyDto companyDto) {
        try {
            String res = companyService.addCompany(companyDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failure Occur", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("/change-the-name-of-company")
    public ResponseEntity<String> changeTheNameOfCompany (@RequestParam int regsNumber , @RequestParam String name) throws Exception{
        try {
            String res =  companyService.changeName(regsNumber, name);
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Registration Number Not Found" , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-the-name")
    public ResponseEntity<?> getTheList (){
        try{
            List<Company> companyList = companyService.getTheList() ;
            return new ResponseEntity<>(companyList , HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("List Not Found" , HttpStatus.BAD_REQUEST) ;
        }
    }

    @DeleteMapping("/delete-the-company")
    public ResponseEntity<String> deleteTheCompany (@RequestParam int regsNumber) throws  Exception{
        try{
            String res = companyService.deleteCompany(regsNumber) ;
            return new ResponseEntity<>(  res , HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Company Not Found " , HttpStatus.NOT_FOUND) ;
        }
    }




}
