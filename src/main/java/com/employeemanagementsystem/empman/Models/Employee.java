package com.employeemanagementsystem.empman.Models;

import com.employeemanagementsystem.empman.Enums.Designation;
import com.employeemanagementsystem.empman.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId ;

    private String employeeName ;

    @Enumerated(EnumType.STRING)
    private Gender gender ;

    private  int age ;

    @Enumerated(EnumType.STRING)
    private Designation designation ;


    private int departmentId ;

    private String departmentName ;

    private String companyName ;

    private int salary ;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Company company;
}
