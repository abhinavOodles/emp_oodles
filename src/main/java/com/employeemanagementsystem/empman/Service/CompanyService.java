package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Exception.RegistrationNumberNotFound;
import com.employeemanagementsystem.empman.Models.Company;
import com.employeemanagementsystem.empman.Repository.CompanyRepo;
import com.employeemanagementsystem.empman.Transformers.CompanyTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo companyRepo  ;
    public void add(addCompanyDto company) {
       Company company1 = CompanyTransformer.convertDtoTOEntity(company) ;
       companyRepo.save(company1) ;
    }

    public String changeName(int registrationNumber, String name) throws RegistrationNumberNotFound {
        Optional<Company> optionalCompany = companyRepo.findById(registrationNumber) ;
        if (optionalCompany.isEmpty()){
            throw new RegistrationNumberNotFound("Wrong Registration Number") ;
        }
        else{
            Company company = optionalCompany.get() ;
            company.setCompanyName(name);
            companyRepo.save(company) ;
            return "Company with Registration Number " + registrationNumber + " now named as "+ name ;
        }
    }
}
