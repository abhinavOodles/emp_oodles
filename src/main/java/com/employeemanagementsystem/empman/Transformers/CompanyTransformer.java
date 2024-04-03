package com.employeemanagementsystem.empman.Transformers;

import com.employeemanagementsystem.empman.Dtos.addCompanyDto;
import com.employeemanagementsystem.empman.Models.Company;

import java.time.LocalDate;

public class CompanyTransformer {
    public static Company convertDtoTOEntity (addCompanyDto companyDto){
        Company company = Company.builder().registrationNumber(companyDto.getRegistrationNumber())
                .yearOFRegistration(LocalDate.ofEpochDay(companyDto.getYearOfRegistration()))
                .companyName(companyDto.getCompanyName())
                .build() ;

        return company ;
    }
}
