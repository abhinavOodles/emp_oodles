package com.employeemanagementsystem.empman.Dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class addCompanyDto {
    private int registrationNumber;
    private LocalDate yearOfRegistration  ;
    private String companyName  ;


}
