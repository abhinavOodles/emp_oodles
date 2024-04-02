package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Models.Company;
import com.employeemanagementsystem.empman.Repository.CompanyRepo;
import com.employeemanagementsystem.empman.Transformers.CompanyTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo companyRepo  ;
    public void add(addCompanyDto company) {
       Company company1 = CompanyTransformer.convertDtoTOEntity(company) ;
       companyRepo.save(company1) ;
    }
}
