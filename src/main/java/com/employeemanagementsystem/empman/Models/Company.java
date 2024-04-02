package com.employeemanagementsystem.empman.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationNumber ;

    private LocalDate yearOFRegistration ;

    private int numberOfEmployees ;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>() ;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL)
    private List<Employee> employeeList = new ArrayList<>() ;



}
