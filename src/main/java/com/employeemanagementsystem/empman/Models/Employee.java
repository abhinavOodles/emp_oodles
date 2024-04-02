package com.employeemanagementsystem.empman.Models;

import com.employeemanagementsystem.empman.Enums.Designation;
import com.employeemanagementsystem.empman.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @Column(nullable = false)
    private String companyName ;

    @ManyToOne
    Department department ;

    @OneToOne
    Company company ;
}
