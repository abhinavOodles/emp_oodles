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

    //add-company in the database
    @PostMapping("/add-company")
    public ResponseEntity<String> addCompany (@RequestBody addCompanyDto companyDto) {
        try {
            String res = companyService.addCompany(companyDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failure Occur", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    //update the name of the company in the database
    @PutMapping("/change-the-name-of-company")
    public ResponseEntity<String> changeTheNameOfCompany (@RequestParam int regsNumber , @RequestParam String name){
        try {
            String res =  companyService.changeName(regsNumber, name);
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Registration Number Not Found" , HttpStatus.BAD_REQUEST);
        }

    }

    //get the list of all the company existing in database
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

    // delete the company name with company-Id
    @DeleteMapping("/delete-the-company")
    public ResponseEntity<String> deleteTheCompany (@RequestParam int regsNumber)   {
        try{
            String res = companyService.deleteCompany(regsNumber) ;
            return new ResponseEntity<>(  res , HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>("Company Not Found " , HttpStatus.NOT_FOUND) ;
        }
    }
       // delete all the existing company in database
        @DeleteMapping("/delete-all-company")
        public ResponseEntity<String> deleteAllCompany (){
            String res =  companyService.deleteAllCompany();
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED);
        }


}
